package document.types.test.routines;

import document.types.DocTypSeparator;
import document.types.DocTypes;
import document.types.DocTypDetection;
import document.types.pdf.Pdf;
import document.types.pdf.PdfAbruf;
import tesseract.ImageProcessing;

import java.io.File;
import java.util.List;

public class DocTypSeparator_getAbrufFiles {
    public static void main(String[] args) throws Exception {
        String directoryPath = new String("images");

        // Create file array with all files in folder
        File dir = new File(directoryPath);
        File[] docFiles = dir.listFiles();

        // Create an Instance of the ImageProcessing Class and get DataStringArr from all files in directoryPath
        String[] docStrings = ImageProcessing.getDataStringArr(docFiles);

        // Create an Instance of the TypDetection Class and get DataStringArr from all files in directoryPath
        DocTypes[] dataTypsArr = DocTypDetection.getDocTypes(docStrings);

        // Get PdfAbruf Files based on the Datatyps and the
        DocTypSeparator docTypSeparator = new DocTypSeparator(docFiles, dataTypsArr, docStrings);
        List<Pdf> abrufFiles = docTypSeparator.getAbrufFiles();

        // Test the List of the abrufFiles by printing the name
        for (Pdf abruf : abrufFiles) {
            System.out.println(abruf.getName());
        }
        



    }
}
