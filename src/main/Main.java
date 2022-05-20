package main;

import document.routines.AbrufRoutine;
import document.routines.WeGlsMailRoutine;
import document.types.DocTypSeparator;
import document.types.DocTypes;
import document.types.DocTypDetection;
import document.types.pdf.Pdf;
import tesseract.ImageProcessing;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Define the Directory where the files are saved
        String directoryPath = new String("C:\\Users\\61044555\\Desktop\\Abrufe");

        // Create file array with all files in folder
        File dir = new File(directoryPath);
        File[] docFiles = dir.listFiles();


        // Create an Instance of the ImageProcessing Class and get DataStringArr from all files in directoryPath
        String[] docStrings = ImageProcessing.getDataStringArr(docFiles);

        // Get the fileTyps from all files in directoryPath based on there DataString
        DocTypes[] docTypes = DocTypDetection.getDocTypes(docStrings);


        // Create an Instance of DocumentTyps and get the individual DocumentFiles
        DocTypSeparator docTypSeparator = new DocTypSeparator(docFiles,  docTypes, docStrings);
        List<Pdf> pdfAbrufe = docTypSeparator.getAbrufFiles();
        List<Pdf> pdfWeGlsMails = docTypSeparator.getWeGlsMail();


        AbrufRoutine abrufeRoutine = new AbrufRoutine(pdfAbrufe);
        abrufeRoutine.execute();

        WeGlsMailRoutine routineWeGlsMail = new WeGlsMailRoutine(pdfWeGlsMails);
        routineWeGlsMail.execute();
    }
}
