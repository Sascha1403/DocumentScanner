package document.types;

import document.analyse.abruf.AbrufAnalysis;
import document.types.pdf.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// This class takes all Files. The attributes of the class are list with the different
public class DocTypSeparator {
    private File[] docFiles;
    private String[] docStrings;
    private DocTypes[] docTypes;

    public DocTypSeparator(File[] docFiles, DocTypes[] docTypes, String[] docStrings) {
        this.docStrings = docStrings;
        this.docTypes = docTypes;
        this.docFiles = docFiles;
    }

    // return a List of PdfAbruf to initialize the list of PdfAbrufe
    public List<Pdf> getAbrufFiles() {
        int index = 0;
        List<Pdf> pdfAbrufList = new ArrayList<Pdf>();

        for (DocTypes datatyp : docTypes) {
            if (datatyp == DocTypes.Abruf) {
                PdfAbruf pdfAbruf = new PdfAbruf(docFiles[index].getAbsolutePath(), docStrings[index]);
                pdfAbrufList.add(pdfAbruf);
            }
            index++;
        }
        return pdfAbrufList;
    }

    // return a List of PdfWeGlsMail to initialize the list of PdfAbrufe
    public List<Pdf> getWeGlsMail() throws IOException {
        int index = 0;
        List<Pdf> pdfWeGlsMailList = new ArrayList<Pdf>();

        for (DocTypes datatyp : docTypes) {
            if (datatyp == DocTypes.WeGlsMail) {
                PdfWeGlsMail pdfWeGlsMail = new PdfWeGlsMail(docFiles[index].getAbsolutePath(), docStrings[index]);
                pdfWeGlsMailList.add(pdfWeGlsMail);
            }
            index++;
        }
        return pdfWeGlsMailList;
    }

    // return a List of PdfWeGlsMail to initialize the list of PdfAbrufe
    public List<Pdf> getWeHIL() throws IOException {
        int index = 0;
        List<Pdf> PdfWeHilList = new ArrayList<Pdf>();

        for (DocTypes datatyp : docTypes) {
            if (datatyp == DocTypes.WeHIL) {
                PdfWeHIL pdfWeHIL = new PdfWeHIL(docFiles[index].getAbsolutePath(), docStrings[index]);
                PdfWeHilList.add(pdfWeHIL);
            }
            index++;
        }
        return PdfWeHilList;
    }

    // return a List of PdfWeGlsMail to initialize the list of PdfAbrufe
    public List<Pdf> getMeldungInst() throws IOException {
        int index = 0;
        List<Pdf> PdfMeldungInstList = new ArrayList<Pdf>();

        for (DocTypes datatyp : docTypes) {
            if (datatyp == DocTypes.MeldungInst) {
                PdfMeldungInst pdfMeldungInst = new PdfMeldungInst(docFiles[index].getAbsolutePath(), docStrings[index]);
                PdfMeldungInstList.add(pdfMeldungInst);
            }
            index++;
        }
        return PdfMeldungInstList;
    }
}
