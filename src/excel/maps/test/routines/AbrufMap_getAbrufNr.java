package excel.maps.test.routines;

import document.types.DocTypDetection;
import document.types.DocTypSeparator;
import document.types.DocTypes;
import document.types.pdf.Pdf;
import excel.maps.AbrufMapSheet1;
import tesseract.ImageProcessing;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AbrufMap_getAbrufNr {
    public static void main(String[] args) throws Exception {
        String directoryPath = new String("images");
        File dir = new File(directoryPath);

        // Create file array with all files in folder
        File[] docFiles = dir.listFiles();

        // Create an Instance of the ImageProcessing Class and get DataStringArr from all files in directoryPath
        String[] docStrings = ImageProcessing.getDataStringArr(docFiles);

        // Get the fileTyps from all files in directoryPath based on there DataString
        DocTypes[] docTypes = DocTypDetection.getDocTypes(docStrings);

        // Create an Instance of DocumentTyps and get the individual DocumentFiles
        DocTypSeparator docTypSeparator = new DocTypSeparator(docFiles,  docTypes, docStrings);
        List<Pdf> abrufFiles = docTypSeparator.getAbrufFiles();
        AbrufMapSheet1 classAbrufMapSheet1 = new AbrufMapSheet1(abrufFiles);
        Long[] AbrufNr = classAbrufMapSheet1.getAbrufNr();
        System.out.println(Arrays.toString(AbrufNr));
    }
}