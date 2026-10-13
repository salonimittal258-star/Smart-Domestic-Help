import pandas as pd
import joblib

from sklearn.model_selection import train_test_split
from sklearn.compose import ColumnTransformer
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import OneHotEncoder
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error, r2_score


# -----------------------------------
# 1. LOAD DATASET
# -----------------------------------

data = pd.read_csv(
    "smart_domestic_help_price_dataset.csv"
)

print("Dataset loaded successfully!")
print("Rows:", len(data))
print("Columns:", len(data.columns))


# -----------------------------------
# 2. INPUT FEATURES
# -----------------------------------

features = [
    "service",
    "locality",
    "service_type",
    "quantity",
    "urgency",
    "house_size",
    "frequency",
    "material_required"
]

target = "estimated_price"


X = data[features]
y = data[target]


# -----------------------------------
# 3. CATEGORICAL FEATURES
# -----------------------------------

categorical_features = [
    "service",
    "locality",
    "service_type",
    "quantity",
    "urgency",
    "house_size",
    "frequency",
    "material_required"
]


# -----------------------------------
# 4. PREPROCESSING
# -----------------------------------

preprocessor = ColumnTransformer(

    transformers=[

        (
            "categorical",

            OneHotEncoder(
                handle_unknown="ignore"
            ),

            categorical_features
        )

    ]
)


# -----------------------------------
# 5. RANDOM FOREST REGRESSOR
# -----------------------------------

model = RandomForestRegressor(

    n_estimators=200,

    random_state=42,

    max_depth=15,

    min_samples_split=2,

    n_jobs=-1
)


# -----------------------------------
# 6. PIPELINE
# -----------------------------------

pipeline = Pipeline(

    steps=[

        (
            "preprocessor",
            preprocessor
        ),

        (
            "model",
            model
        )

    ]
)


# -----------------------------------
# 7. TRAIN / TEST SPLIT
# -----------------------------------

X_train, X_test, y_train, y_test = train_test_split(

    X,
    y,

    test_size=0.20,

    random_state=42
)


print("\nTraining model...")


# -----------------------------------
# 8. TRAIN MODEL
# -----------------------------------

pipeline.fit(
    X_train,
    y_train
)


print("Training completed!")


# -----------------------------------
# 9. PREDICTION
# -----------------------------------

y_pred = pipeline.predict(
    X_test
)


# -----------------------------------
# 10. EVALUATION
# -----------------------------------

mae = mean_absolute_error(
    y_test,
    y_pred
)

r2 = r2_score(
    y_test,
    y_pred
)


# -----------------------------------
# 11. ACCURACY %
# -----------------------------------

accuracy = r2 * 100


print("\n=================================")
print("       MODEL PERFORMANCE")
print("=================================")

print(
    "Mean Absolute Error:",
    round(mae, 2)
)

print(
    "R2 Score:",
    round(r2, 4)
)

print(
    "Accuracy:",
    round(accuracy, 2),
    "%"
)


# -----------------------------------
# 12. SAVE MODEL
# -----------------------------------

joblib.dump(
    pipeline,
    "price_model.pkl"
)


print("\n=================================")
print("MODEL SAVED SUCCESSFULLY")
print("=================================")

print(
    "File: price_model.pkl"
)


# -----------------------------------
# 13. TEST SAMPLE
# -----------------------------------

sample = pd.DataFrame({

    "service": [
        "Cleaning"
    ],

    "locality": [
        "Rajpur Road"
    ],

    "service_type": [
        "Deep Cleaning"
    ],

    "quantity": [
        "2 BHK"
    ],

    "urgency": [
        "Normal"
    ],

    "house_size": [
        "2 BHK"
    ],

    "frequency": [
        "One-time"
    ],

    "material_required": [
        "No"
    ]

})


# -----------------------------------
# 14. SAMPLE PREDICTION
# -----------------------------------

sample_prediction = pipeline.predict(
    sample
)


print("\n=================================")
print("       SAMPLE PREDICTION")
print("=================================")

print(
    "Service: Cleaning"
)

print(
    "Locality: Rajpur Road"
)

print(
    "Estimated Price: ₹",
    round(sample_prediction[0])
)

print("=================================")