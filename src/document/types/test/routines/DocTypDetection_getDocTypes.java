package document.types.test.routines;

import document.types.DocTypDetection;
import document.types.DocTypes;
import tesseract.ImageProcessing;

import java.io.File;

public class DocTypDetection_getDocTypes {
    public static void main(String[] args) throws Exception {
        String directoryPath = new String("images");

        // Create file array with all files in folder
        File dir = new File(directoryPath);
        File[] docFiles = dir.listFiles();

        // Create an Instance of the ImageProcessing Class and get DataStringArr from all files in directoryPath
        String[] docStrings = ImageProcessing.getDataStringArr(docFiles);

        // Get the fileTyps from all files in directoryPath based on there DataString
        DocTypes[] docTypes = DocTypDetection.getDocTypes(docStrings);

        // Test Methode by printing the name of the docTypes
        for (DocTypes typ: docTypes) {
            System.out.println(typ.toString());
        }
    }
}

