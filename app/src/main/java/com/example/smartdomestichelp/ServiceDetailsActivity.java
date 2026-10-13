package com.example.smartdomestichelp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class ServiceDetailsActivity extends Activity {

    TextView tvServiceName;
    TextView tvLocality;

    LinearLayout formContainer;

    EditText etAdditionalDetails;
    Button btnContinue;

    String service;
    String locality;

    ArrayList<Spinner> spinnerList = new ArrayList<>();

    ArrayList<String> questionList = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        service = getIntent().getStringExtra("service");
        locality = getIntent().getStringExtra("locality");

        tvServiceName = findViewById(R.id.tvServiceName);
        tvLocality = findViewById(R.id.tvLocality);

        formContainer = findViewById(R.id.formContainer);

        etAdditionalDetails =
                findViewById(R.id.etAdditionalDetails);

        btnContinue =
                findViewById(R.id.btnContinue);

        if (service != null) {
            tvServiceName.setText(service);
        }

        if (locality != null) {
            tvLocality.setText("📍 " + locality);
        }

        createServiceForm();

        btnContinue.setOnClickListener(
                v -> openPriceEstimate()
        );
    }


    // CREATE SERVICE FORM

    private void createServiceForm() {

        formContainer.removeAllViews();

        spinnerList.clear();
        questionList.clear();

        if (service == null) {
            return;
        }


        switch (service.toLowerCase()) {

            case "plumbing":

                addSpinner(
                        "What problem are you facing?",
                        new String[]{
                                "Select problem",
                                "Tap Leakage",
                                "Pipe Leakage",
                                "Blocked Drain",
                                "Water Tank Problem",
                                "Bathroom Fitting",
                                "Other"
                        }
                );

                addSpinner(
                        "Number of fittings / pipes",
                        new String[]{
                                "Select quantity",
                                "1",
                                "2",
                                "3",
                                "4+"
                        }
                );

                addSpinner(
                        "Is material required?",
                        new String[]{
                                "Select option",
                                "No",
                                "Yes",
                                "Not Sure"
                        }
                );

                addSpinner(
                        "How urgent is the service?",
                        new String[]{
                                "Select urgency",
                                "Normal",
                                "Urgent",
                                "Emergency"
                        }
                );

                break;


            case "cleaning":

                addSpinner(
                        "What type of cleaning do you need?",
                        new String[]{
                                "Select cleaning type",
                                "Regular Cleaning",
                                "Deep Cleaning",
                                "Kitchen Cleaning",
                                "Bathroom Cleaning",
                                "Full House Cleaning"
                        }
                );

                addSpinner(
                        "Number of rooms",
                        new String[]{
                                "Select rooms",
                                "1",
                                "2",
                                "3",
                                "4+"
                        }
                );

                addSpinner(
                        "Number of bathrooms",
                        new String[]{
                                "Select bathrooms",
                                "1",
                                "2",
                                "3+"
                        }
                );

                addSpinner(
                        "House size",
                        new String[]{
                                "Select house size",
                                "1 BHK",
                                "2 BHK",
                                "3 BHK",
                                "4 BHK+"
                        }
                );

                break;


            case "electrician":

                addSpinner(
                        "What electrical work is needed?",
                        new String[]{
                                "Select problem",
                                "Fan",
                                "Light",
                                "Switch",
                                "Socket",
                                "Wiring",
                                "MCB",
                                "Appliance Connection",
                                "Other"
                        }
                );

                addSpinner(
                        "Number of items",
                        new String[]{
                                "Select quantity",
                                "1",
                                "2",
                                "3",
                                "4+"
                        }
                );

                addSpinner(
                        "Type of work",
                        new String[]{
                                "Select work type",
                                "Repair",
                                "New Installation"
                        }
                );

                addSpinner(
                        "Is material required?",
                        new String[]{
                                "Select option",
                                "No",
                                "Yes",
                                "Not Sure"
                        }
                );

                addSpinner(
                        "How urgent is the service?",
                        new String[]{
                                "Select urgency",
                                "Normal",
                                "Urgent",
                                "Emergency"
                        }
                );

                break;


            case "cook":

                addSpinner(
                        "How many family members?",
                        new String[]{
                                "Select members",
                                "1–2",
                                "3–4",
                                "5–6",
                                "7+"
                        }
                );

                addSpinner(
                        "Food preference",
                        new String[]{
                                "Select food preference",
                                "Vegetarian",
                                "Non-Vegetarian",
                                "Both"
                        }
                );

                addSpinner(
                        "Which meal do you need?",
                        new String[]{
                                "Select meal",
                                "Breakfast",
                                "Lunch",
                                "Dinner",
                                "Full Day"
                        }
                );

                addSpinner(
                        "How many dishes?",
                        new String[]{
                                "Select number of dishes",
                                "1–2",
                                "3–4",
                                "5–6",
                                "7+"
                        }
                );

                addSpinner(
                        "How often do you need the cook?",
                        new String[]{
                                "Select frequency",
                                "One-time",
                                "Daily",
                                "Weekly"
                        }
                );

                break;


            case "maid":

                addSpinner(
                        "What household work is required?",
                        new String[]{
                                "Select work",
                                "Sweeping",
                                "Mopping",
                                "Utensils",
                                "Laundry",
                                "Dusting",
                                "Full Household Work"
                        }
                );

                addSpinner(
                        "How many family members?",
                        new String[]{
                                "Select members",
                                "1–2",
                                "3–4",
                                "5–6",
                                "7+"
                        }
                );

                addSpinner(
                        "House size",
                        new String[]{
                                "Select house size",
                                "1 BHK",
                                "2 BHK",
                                "3 BHK",
                                "4 BHK+"
                        }
                );

                addSpinner(
                        "How often is help needed?",
                        new String[]{
                                "Select frequency",
                                "One-time",
                                "Daily",
                                "Weekly"
                        }
                );

                addSpinner(
                        "Required working hours",
                        new String[]{
                                "Select hours",
                                "1–2 hours",
                                "3–4 hours",
                                "5–6 hours",
                                "7+ hours"
                        }
                );

                break;


            case "babysitter":

                addSpinner(
                        "Number of children",
                        new String[]{
                                "Select children",
                                "1",
                                "2",
                                "3+"
                        }
                );

                addSpinner(
                        "Age group",
                        new String[]{
                                "Select age",
                                "0–2 years",
                                "3–5 years",
                                "6–10 years",
                                "11+ years"
                        }
                );

                addSpinner(
                        "Required duration",
                        new String[]{
                                "Select duration",
                                "2–4 hours",
                                "4–8 hours",
                                "8+ hours"
                        }
                );

                addSpinner(
                        "Type of service",
                        new String[]{
                                "Select type",
                                "One-time",
                                "Regular"
                        }
                );

                addSpinner(
                        "Required timing",
                        new String[]{
                                "Select timing",
                                "Day",
                                "Night",
                                "Both"
                        }
                );

                break;


            case "carpenter":

                addSpinner(
                        "What type of carpentry work?",
                        new String[]{
                                "Select work",
                                "Furniture Repair",
                                "Door",
                                "Cabinet",
                                "Bed",
                                "Table / Chair",
                                "Installation",
                                "Other"
                        }
                );

                addSpinner(
                        "Number of items",
                        new String[]{
                                "Select quantity",
                                "1",
                                "2",
                                "3",
                                "4+"
                        }
                );

                addSpinner(
                        "Type of work",
                        new String[]{
                                "Select work type",
                                "Repair",
                                "New Work"
                        }
                );

                addSpinner(
                        "Who provides the material?",
                        new String[]{
                                "Select option",
                                "Customer",
                                "Worker",
                                "Not Sure"
                        }
                );

                break;


            default:

                addSpinner(
                        "Select requirement",
                        new String[]{
                                "General Service",
                                "Repair",
                                "Installation",
                                "Other"
                        }
                );

                break;
        }
    }


    // ---------------------------------------
    // ADD SPINNER
    // ---------------------------------------

    private void addSpinner(
            String question,
            String[] options
    ) {

        TextView label =
                new TextView(this);

        label.setText(question);
        label.setTextSize(16);
        label.setTextColor(
                Color.rgb(55, 55, 55)
        );

        label.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        LinearLayout.LayoutParams labelParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        labelParams.setMargins(
                0,
                12,
                0,
                6
        );

        label.setLayoutParams(labelParams);

        formContainer.addView(label);


        Spinner spinner =
                new Spinner(this);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        options
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);


        LinearLayout.LayoutParams spinnerParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        55
                );

        spinnerParams.setMargins(
                0,
                0,
                0,
                10
        );

        spinner.setLayoutParams(
                spinnerParams
        );

        formContainer.addView(
                spinner
        );


        spinnerList.add(spinner);
        questionList.add(question);
    }


    // ---------------------------------------
    // SEND DETAILS TO PRICE SCREEN
    // ---------------------------------------

    private void openPriceEstimate() {

        String additionalDetails =
                etAdditionalDetails
                        .getText()
                        .toString()
                        .trim();


        Intent intent =
                new Intent(
                        ServiceDetailsActivity.this,
                        PredictionActivity.class
                );


        intent.putExtra(
                "service",
                service
        );

        intent.putExtra(
                "locality",
                locality
        );

        intent.putExtra(
                "details",
                additionalDetails
        );


        // ---------------------------------------
        // SEND EVERY SELECTED OPTION
        // ---------------------------------------

        for (int i = 0;
             i < spinnerList.size();
             i++) {

            String selectedValue =
                    spinnerList
                            .get(i)
                            .getSelectedItem()
                            .toString();

            String question =
                    questionList.get(i);


            intent.putExtra(
                    "detail_" + i,
                    selectedValue
            );

            intent.putExtra(
                    "question_" + i,
                    question
            );
        }


        intent.putExtra(
                "total_details",
                spinnerList.size()
        );


        startActivity(intent);
    }
}
