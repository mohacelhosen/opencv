package manipulation;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.CvType;

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


        // Convert the image to grayscale by averaging RGB values
        Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);

        for (int row = 0; row<image.rows(); row++){
            for (int col = 0; col<image.cols(); col++){
                double[] singlePixel = image.get(row, col);
                // Convert RGB to grayscale using luminosity method: 0.21 R + 0.72 G + 0.07 B
                double grayValue  = 0.21 * singlePixel[0] + 0.72 * singlePixel[1] + 0.07 * singlePixel[2];
                grayImage.put(row,col,grayValue);
            }
        }

        // Display the grayscale image
        HighGui.imshow("Grayscale Image", grayImage);

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

        // Destroy the window displaying the  images
        HighGui.destroyAllWindows();

        // Exit the program with status code 200
//        System.exit(200);
    }
}
