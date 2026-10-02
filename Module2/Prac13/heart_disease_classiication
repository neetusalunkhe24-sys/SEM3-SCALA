from pyspark.sql import SparkSession
from pyspark.ml import Pipeline
from pyspark.ml.feature import VectorAssembler
from pyspark.ml.classification import LogisticRegression
from pyspark.ml.evaluation import MulticlassClassificationEvaluator

spark = SparkSession.builder \
    .appName("Heart Disease Classification") \
    .master("local[*]") \
    .getOrCreate()

data = spark.read \
    .option("header", "true") \
    .option("inferSchema", "true") \
    .csv("Heart Disease Dataset.csv")

print("========== HEART DISEASE DATASET ==========")
data.show(10)


print("Number of rows:", data.count())
print("Number of columns:", len(data.columns))

print("========== DATASET SCHEMA ==========")
data.printSchema()

feature_columns = [
    "age",
    "sex",
    "cp",
    "trestbps",
    "chol",
    "fbs",
    "restecg",
    "thalach",
    "exang",
    "oldpeak",
    "slope",
    "ca",
    "thal"
]

assembler = VectorAssembler(
    inputCols=feature_columns,
    outputCol="features"
)

lr = LogisticRegression(
    featuresCol="features",
    labelCol="target"
)

pipeline = Pipeline(
    stages=[
        assembler,
        lr
    ]
)

training_data, testing_data = data.randomSplit(
    [0.8, 0.2],
    seed=42
)


print("========== TRAINING DATA ==========")
print("Training rows:", training_data.count())


print("========== TESTING DATA ==========")
print("Testing rows:", testing_data.count())
model = pipeline.fit(training_data)
predictions = model.transform(testing_data)

print("========== PREDICTIONS ==========")
predictions.select(
    "age",
    "sex",
    "cp",
    "trestbps",
    "chol",
    "target",
    "prediction"
).show(20)

evaluator = MulticlassClassificationEvaluator(
    labelCol="target",
    predictionCol="prediction",
    metricName="accuracy"
)

accuracy = evaluator.evaluate(predictions)


print("========== MODEL EVALUATION ==========")
print("Accuracy:", accuracy)

predictions.select(
    "age",
    "sex",
    "cp",
    "trestbps",
    "chol",
    "target",
    "prediction"
).coalesce(1) \
    .write \
    .mode("overwrite") \
    .option("header", "true") \
    .csv("heart_disease_predictions")

print("========== OUTPUT SAVED SUCCESSFULLY ==========")
print("Output folder: heart_disease_predictions")

spark.stop()
