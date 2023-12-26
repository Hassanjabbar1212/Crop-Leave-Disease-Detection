def detection(img_path):
    import cv2
    import numpy as np

    from yolov8 import YOLOv8

# Initialize yolov8 object detector
    model_path = "extrabest.onnx"
    yolov8_detector = YOLOv8(model_path, conf_thres=0.5, iou_thres=0.3)
    img = cv2.imread(img_path)

# Detect Objects
    boxes, scores, class_ids = yolov8_detector(img)
    if len(class_ids) == 0:
       class1 = np.append(class_ids,4)
    # Handle the empty array case here (e.g., return an empty list)
       return int(class1)
    else:
    # Convert the array to a list
    
        return max(class_ids.tolist())
