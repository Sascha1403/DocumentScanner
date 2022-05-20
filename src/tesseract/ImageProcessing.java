package tesseract;

import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class ImageProcessing {


    public static String[] getDataStringArr(File[] fileList) {


        // Get all String Representation from the files
        int index = 0;
        String[] dataStringArr = new String[fileList.length];

        for (File file : fileList) {
            String dataString;

            dataString = getStringRepresentation(file);
            dataStringArr[index++] = dataString;
        }

        return dataStringArr;
    }


    protected static String getStringRepresentation(File imageFile) {

        // Create an empty String and an Instance of Tesseract
        String result = "";
        Tesseract instance = new Tesseract();

        // Convert the imageFile to an String
        try {
            result = instance.doOCR(imageFile);
            result.toString();


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return result;
    }
}
