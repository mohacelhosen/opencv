package ai;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.InputStream;
import java.net.URI;

public class Sample {
    public static void main(String[] args) {

        // Load OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Load the image directly from the internet
        String imageUrl = "https://img.freepik.com/free-vector/anime-chibi-happy-boy-character_18591-82514.jpg?t=st=1710142772~exp=1710146372~hmac=006116ae32ec6f592f1b34eb6645f4fad10e65028422ba0e4f7688e60fd512df&w=360";
        Mat image = loadMatFromUrl(imageUrl);

        // Check if the image is loaded successfully
        if (image.empty()) {
            System.out.println("Fail to load image from URL: " + imageUrl);
            System.exit(1);
        }


        //Display the loaded image
        HighGui.imshow("Load Chibi Image", image);
        HighGui.waitKey();

        // Release the image resources
        image.release();

    }

    private static Mat loadMatFromUrl(String imageUrl) {
        try {
            URI uri = URI.create(imageUrl);
            // Open an input stream from the URL
            try (InputStream inputStream = uri.toURL().openStream()) {
                byte[] imageData = inputStream.readAllBytes();
                MatOfByte matOfByte = new MatOfByte(imageData);
                return Imgcodecs.imdecode(matOfByte, Imgcodecs.IMREAD_UNCHANGED);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Mat();
        }
    }
}
