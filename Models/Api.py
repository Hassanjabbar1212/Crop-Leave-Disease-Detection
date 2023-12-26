from flask import Flask, request, jsonify
from image_object_detection import detection
import os

UPLOAD_FOLDER = './images'

app = Flask(__name__, static_url_path='/static', static_folder='web')
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route('/detection', methods=['POST'])
def create_user():
    file1 = request.files['file1']
    path = os.path.join(app.config['UPLOAD_FOLDER'], file1.filename)
    file1.save(path)
    # #image PAth
    results = detection(path)
    results = jsonify({"result": results})

    #Convert the results into JSON format

    # do something with the data
    results.headers.add("Access-Control-Allow-Origin", "*")
    return results

if __name__ == '__main__':
    app.run(debug = True, host = "192.168.10.14",port=5001)