package com.example.smartdomestichelp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class OfflinePricePredictor {

    private JSONObject modelData;
    private JSONArray trees;

    public OfflinePricePredictor(Context context) throws Exception {

        InputStream inputStream =
                context.getAssets().open("price_model.json");

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();

        String json = new String(buffer, StandardCharsets.UTF_8);

        modelData = new JSONObject(json);
        trees = modelData.getJSONArray("trees");
    }

    public double predict(
            String service,
            String locality,
            String serviceType,
            String quantity,
            String urgency,
            String houseSize,
            String frequency,
            String materialRequired) {

        try {

            String[] input = {
                    service,
                    locality,
                    serviceType,
                    quantity,
                    urgency,
                    houseSize,
                    frequency,
                    materialRequired
            };

            double total = 0.0;

            for (int i = 0; i < trees.length(); i++) {

                JSONObject tree = trees.getJSONObject(i);

                JSONArray childrenLeft =
                        tree.getJSONArray("children_left");

                JSONArray childrenRight =
                        tree.getJSONArray("children_right");

                JSONArray feature =
                        tree.getJSONArray("feature");

                JSONArray threshold =
                        tree.getJSONArray("threshold");

                JSONArray value =
                        tree.getJSONArray("value");

                int node = 0;

                while (true) {

                    int left = childrenLeft.getInt(node);
                    int right = childrenRight.getInt(node);

                    // Leaf node
                    if (left == -1 && right == -1) {
                        total += value.getDouble(node);
                        break;
                    }

                    int featureIndex = feature.getInt(node);
                    double splitValue = threshold.getDouble(node);

                    double featureValue =
                            getEncodedFeatureValue(
                                    featureIndex,
                                    input
                            );

                    if (featureValue <= splitValue) {
                        node = left;
                    } else {
                        node = right;
                    }
                }
            }

            return total / trees.length();

        } catch (Exception e) {

            e.printStackTrace();

            return 0.0;
        }
    }

    private double getEncodedFeatureValue(
            int featureIndex,
            String[] input) throws Exception {

        int currentIndex = 0;

        String[] featureNames = {
                "service",
                "locality",
                "service_type",
                "quantity",
                "urgency",
                "house_size",
                "frequency",
                "material_required"
        };

        JSONObject categoriesObject =
                modelData.getJSONObject("categories");

        for (int i = 0; i < featureNames.length; i++) {

            JSONArray categories =
                    categoriesObject.getJSONArray(
                            featureNames[i]
                    );

            for (int j = 0; j < categories.length(); j++) {

                if (currentIndex == featureIndex) {

                    String category =
                            categories.getString(j);

                    if (input[i].equals(category)) {
                        return 1.0;
                    } else {
                        return 0.0;
                    }
                }

                currentIndex++;
            }
        }

        return 0.0;
    }
}