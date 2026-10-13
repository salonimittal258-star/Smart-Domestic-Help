from flask import Flask, request, jsonify
from flask_cors import CORS
import joblib
import pandas as pd

app = Flask(__name__)
CORS(app)

# ---------------------------------------
# LOAD MODELS
# ---------------------------------------

service_model = joblib.load(
    "smart_domestic_help_model.pkl"
)

price_model = joblib.load(
    "price_model.pkl"
)


# ---------------------------------------
# HOME
# ---------------------------------------

@app.route("/")
def home():
    return "Smart Domestic Help ML API is running!"


# ---------------------------------------
# OLD SERVICE PREDICTION
# ---------------------------------------

@app.route("/predict", methods=["POST"])
def predict_service():

    try:

        data = request.get_json()

        features = [
            data.get("request_id", 0),
            data.get("user_age", 25),
            data.get("household_size", 3),
            data.get("location_zone", "Urban"),
            data.get("preferred_time_slot", "Morning"),
            data.get("request_hour", 10),
            data.get("is_weekend", 0),
            data.get("appliance_issue", 0),
            data.get("furniture_issue", 0),
            data.get("wiring_issue", 0),
            data.get("cleaning_need", 0),
            data.get("wall_condition", 0),
            data.get("pest_level", 0),
            data.get("water_leakage", 0)
        ]

        prediction = service_model.predict([features])

        return jsonify({
            "predicted_service": str(prediction[0])
        })

    except Exception as e:

        return jsonify({
            "error": str(e)
        }), 500


# ---------------------------------------
# PRICE PREDICTION
# ---------------------------------------

@app.route("/predict_price", methods=["POST"])
def predict_price():

    try:

        data = request.get_json()

        print("\n========== PRICE REQUEST ==========")
        print(data)

        # --------------------------------
        # CREATE PANDAS DATAFRAME
        # --------------------------------

        input_data = pd.DataFrame([{

            "service": data.get(
                "service",
                "Cleaning"
            ),

            "locality": data.get(
                "locality",
                "Rajpur Road"
            ),

            "service_type": data.get(
                "service_type",
                "Regular Cleaning"
            ),

            "quantity": data.get(
                "quantity",
                "1"
            ),

            "urgency": data.get(
                "urgency",
                "Normal"
            ),

            "house_size": data.get(
                "house_size",
                "2 BHK"
            ),

            "frequency": data.get(
                "frequency",
                "One-time"
            ),

            "material_required": data.get(
                "material_required",
                "No"
            )

        }])

        print("\nData sent to price model:")
        print(input_data)

        # --------------------------------
        # PREDICT PRICE
        # --------------------------------

        predicted_price = price_model.predict(
            input_data
        )[0]

        print(
            "Predicted Price:",
            predicted_price
        )

        return jsonify({

            "estimated_price":
                round(float(predicted_price), 2)

        })

    except Exception as e:

        print("\n========== PRICE ERROR ==========")
        print(str(e))

        return jsonify({

            "error": str(e)

        }), 500


# ---------------------------------------
# RUN SERVER
# ---------------------------------------

if __name__ == "__main__":

    app.run(
        host="0.0.0.0",
        port=5000,
        debug=True
    )