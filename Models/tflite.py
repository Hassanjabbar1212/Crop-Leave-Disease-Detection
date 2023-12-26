import numpy as np
import tensorflow as tf
from PIL import Image

# Load the TFLite model and allocate tensors.
interpreter = tf.lite.Interpreter(model_path="best_int8.tflite")
interpreter.allocate_tensors()

# Get input and output tensors.
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

# Load an image and preprocess it for inference.
image = Image.open("download1.jpg")
image = image.resize((input_details[0]['shape'][1], input_details[0]['shape'][2]))
input_data = np.expand_dims(image, axis=0)
input_data = input_data.astype(input_details[0]['dtype'])

# Run inference on the TFLite model.
interpreter.set_tensor(input_details[0]['index'], input_data)
interpreter.invoke()
output_data = interpreter.get_tensor(output_details[0]['index'])

# Print the detected objects and their scores.
for i in range(output_data.shape[1]):
    class_id = int(output_data[0, i, 1])
    score = float(output_data[0, i, 2])
    bbox = [float(v) for v in output_data[0, i, 3:]]
    print("class={}".format(class_id))
    print("score={}".format(score))
    #print("bbox={}".format(bbox))