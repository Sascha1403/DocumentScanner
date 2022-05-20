package tesseract.test.routines;

import tesseract.ImageProcessing;
import tesseract.LoadData;

import java.awt.*;
import java.io.File;

public class ImageProcessing_getDataStringArr {
    public static void main(String[] args) {
        String directoryPath = new String("C:\\Users\\61044555\\Documents\\DocumentScanner\\images");

        // Create file array with all files in folder
        File dir = new File(directoryPath);
        File[] docFiles = dir.listFiles();

        String[] dataStringArr = ImageProcessing.getDataStringArr(docFiles);
        System.out.println(dataStringArr[2]);
    }
}
