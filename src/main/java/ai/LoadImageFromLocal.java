package ai;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.Arrays;

public class LoadImageFromLocal {
    static {
        // Load OpenCV native library
        System.load("D:\\opencv\\opencv\\build\\java\\x64\\opencv_java490.dll");
    }

    public static void main(String[] args) throws IOException {
        // Load the image
        String imagePath1 = "img/anime-boy.jpg";
        String imagePat2 = "img/anime-chibi.jpg";
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePat2);

        // Check if the images is loaded successfully
        if (image1.empty() && image2.empty()) {
            System.out.println("Failed to load the image");
            System.exit(1);
        }



        // Display the loaded image
        HighGui.imshow("Image 1", image1);
        HighGui.imshow("Image 2", image2);

        int height = image1.rows();
        int width = image1.cols();
        Imgproc.resize(image2, image2, new Size(image1.cols(), image1.rows()));




        Mat concatImage = new Mat();
        Core.hconcat(Arrays.asList(image1, image2), concatImage); //both images should have the same height.
        HighGui.imshow("hconcat image", concatImage);


        System.out.println(image1);
        System.out.println(image2);
        System.out.println(concatImage);

        System.out.println("Waiting for key press...");
        int waitKey = HighGui.waitKey(0);

        if (waitKey != -1) {
            System.out.println("Pressed key: " + waitKey);
        }

        // Clean up resources
        image1.release();
        image2.release();
        HighGui.destroyAllWindows();
        System.out.println("Resources released.");
        System.exit(200);
    }



}
