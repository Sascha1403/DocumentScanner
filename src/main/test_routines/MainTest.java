package main.test_routines;

import document.routines.MeldungInstRoutine;
import document.routines.Routine;
import document.routines.WeGlsMailRoutine;
import document.routines.test_routines.AbrufRoutineTest;
import document.routines.test_routines.WeHILRoutine;
import document.split.DocumentSpliter;
import document.types.DocTypDetection;
import document.types.DocTypSeparator;
import document.types.DocTypes;
import document.types.pdf.*;
import org.apache.commons.io.FileUtils;
import tesseract.ImageProcessing;

import java.io.File;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws Exception {

        // Split and Merge Documents
        String fileDirPath = new String("images"); // Directory where the files are
        DocumentSpliter documentSpliter = new DocumentSpliter(fileDirPath);
        documentSpliter.splitDocuments();
        documentSpliter.mergeDocuments();


        // Define the Directory where the files are saved
        String directoryPath = new String("mergedPdfDoc");

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
        List<Pdf> pdfWeHIL = docTypSeparator.getWeHIL();
        List<Pdf> pdfMeldungInst = docTypSeparator.getMeldungInst();

        AbrufRoutineTest routineAbruf = new AbrufRoutineTest(pdfAbrufe);
        routineAbruf.executeRoutine();

        WeGlsMailRoutine routineWeGlsMail = new WeGlsMailRoutine(pdfWeGlsMails);
        routineWeGlsMail.execute();

        WeHILRoutine routineWeHilMail = new WeHILRoutine(pdfWeHIL);
        routineWeHilMail.execute();

        MeldungInstRoutine routineMeldInst = new MeldungInstRoutine(pdfMeldungInst);
        routineMeldInst.execute();

        FileUtils.cleanDirectory(new File("mergedPdfDoc"));
        FileUtils.cleanDirectory(new File("splittedPdfDoc"));
    }
}
