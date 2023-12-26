import cv2

cap = cv2.VideoCapture('Canelo.mp4')

num = 0

while(cap.isOpened()):
    ret, frame = cap.read()
    num = num + 1
    cv2.imwrite((str(num) + '.jpg'), frame)

    k = cv2.waitKey(0) & 0xFF

    if k==27:
        break

cap.release()
cv2.destroyAllWindows()
