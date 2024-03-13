package manipulation;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageDataManipulation {
    public static void main(String[] args) {
        // img path
        String img = "img/anime-boy.jpg";

        // Load OpenCV native library
        System.load("D:\\opencv\\opencv\\build\\java\\x64\\opencv_java490.dll");

        // Read the image file into a Mat object
        Mat image = Imgcodecs.imread(img);

        // Convert the image data to a byte array
        byte[] imageData = new byte[(int) (image.total() * image.channels())];
        image.get(0, 0, imageData);

        // Create a new Mat object with the same dimensions and type as the original image
        Mat newImageFromArray = new Mat(image.rows(), image.cols(), image.type());

        // Copy the image data from the byte array to the new Mat object
        newImageFromArray.put(0, 0, imageData);

        // Display the new image created from the byte array
        HighGui.imshow("Array image", newImageFromArray);

        // Display the original image
        HighGui.imshow("Original Image", image);

        // Wait for a key press to exit
        HighGui.waitKey(0);

        // Release the memory occupied by the original image
        image.release();

        // Destroy the window displaying the original image
        HighGui.destroyWindow("Original Image");

        // Exit the program with status code 200
        System.exit(200);
    }
}
