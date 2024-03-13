package load;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LoadAllImage {
    public static void main(String[] args) throws IOException {
        String directory = "img";
        List<Path> imageFiles = new ArrayList<>();

        if (Files.isDirectory(Path.of(directory))) {
            List<Path> list = Files.walk(Path.of(directory)).toList();
            for (Path path : list) {
                if (Files.isRegularFile(path)) {
                    imageFiles.add(path);
                }
            }
        }

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        List<Mat> images = new ArrayList<>();
        for (Path path : imageFiles) {
            images.add(Imgcodecs.imread(path.toString()));
        }

        // Check dimensions and data type
        for (int i = 0; i < images.size(); i++) {
            System.out.println("Image " + i + " dimensions: " + images.get(i).rows() + " x " + images.get(i).cols());
            System.out.println("Image " + i + " type: " + CvType.typeToString(images.get(i).type()));
        }

        Size commonSize = new Size(236, 236);

        List<Mat> resizeImages = images.stream().map(singleImage -> {
            Mat resizedImage = new Mat();
            Imgproc.resize(singleImage, resizedImage, commonSize);
            return resizedImage;
        }).toList();


        // Check if all images have the same number of rows
        int firstImageRows = resizeImages.get(0).rows();
        for (int i = 1; i < resizeImages.size(); i++) {
            if (resizeImages.get(i).rows() != firstImageRows) {
                throw new IllegalArgumentException("All images must have the same number of rows.");
            }
        }

        // Check if all images have the same data type
        int firstImageType = resizeImages.get(0).type();
        for (int i = 1; i < resizeImages.size(); i++) {
            if (resizeImages.get(i).type() != firstImageType) {
                throw new IllegalArgumentException("All images must have the same data type.");
            }
        }

        // Concatenate all images horizontally
        Mat result = new Mat();
        Core.hconcat(resizeImages, result);

        // Display the concatenated image
        HighGui.imshow("All Images in One Frame", result);
        HighGui.waitKey();

    }
}
