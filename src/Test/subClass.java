package Test;

import document.types.DocTypDetection;
import document.types.DocTypes;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import tesseract.ImageProcessing;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class subClass{
    public static void main(String[] args) throws Exception {
        int indexSplittedDoc = 0;



        String directoryPath = new String("images");
        // Create file array with all files in folder
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();

        for (File docFile : files) {
            PDDocument document = PDDocument.load(docFile);
            Splitter splitting = new Splitter();

            // Splitting the pages into multiple PDFs
            List<PDDocument> Page = splitting.split(document);
            // Using a iterator to Traverse all pages
            Iterator<PDDocument> iteration
                    = Page.listIterator();

            // Saving each page as an individual document
            while (iteration.hasNext()) {
                PDDocument pd = iteration.next();

                pd.save("splittedPdfDoc/sample_"+ indexSplittedDoc++ +".pdf");
            }
            System.out.println("Splitted Pdf Successfully.");
            document.close();
        }

        String splittedDocPath = new String("splittedPdfDoc");
        File dirSplittedDoc = new File(splittedDocPath);
        File[] splittedDoc = dirSplittedDoc.listFiles();
        Arrays.sort(splittedDoc, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int n1 = extractNumber(o1.getName());
                int n2 = extractNumber(o2.getName());
                return n1 - n2;
            }

            private int extractNumber(String name) {
                int i = 0;
                try {
                    int s = name.indexOf('_')+1;
                    int e = name.lastIndexOf('.');
                    String number = name.substring(s, e);
                    i = Integer.parseInt(number);
                } catch(Exception e) {
                    i = 0; // if filename does not match the format
                    // then default to 0
                }
                return i;
            }
        });



        // Create an Instance of the ImageProcessing Class and get DataStringArr from all files in directoryPath
        String[] docStrings = ImageProcessing.getDataStringArr(splittedDoc);

        // Get the fileTyps from all files in directoryPath based on there DataString
        DocTypes[] docTypes = DocTypDetection.getDocTypes(docStrings);

        int numberMergedDoc = 1;
        for (int i = 0; i < docTypes.length - 1; i++) {
            int counter = i;
            boolean fullDocument = false;

            while (fullDocument == false) {

                if (docTypes[i + 1] != DocTypes.UnknownDocument || i == docTypes.length - 2) {

                    // Initial the Merge object
                    PDFMergerUtility obj = new PDFMergerUtility();
                    obj.setDestinationFileName("mergedPdfDoc/mergedPdf" + numberMergedDoc + ".pdf");

                    // Add all Files which need to be merged
                    for (int k = counter; k <= i; k++) {
                        obj.addSource(splittedDoc[k]);
                    }

                    if (i == docTypes.length - 2)
                        obj.addSource(splittedDoc[docTypes.length - 1]);

                    // Merge Files and break out of while loop
                    obj.mergeDocuments(null);
                    fullDocument = true;
                    numberMergedDoc++;
                } else
                    i++;
            }
        }
    }
}
