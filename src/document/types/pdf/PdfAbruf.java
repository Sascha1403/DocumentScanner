package document.types.pdf;

import document.analyse.abruf.AbrufAnalysis;
import document.types.DocTypes;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.IOException;

public class PdfAbruf extends Pdf {

    public PdfAbruf(String path, String docString) {
        super(path);
        this.docString = docString;
        this.abrufNr = getAbrufNrFromDocString();
        this.kontraktNr = getKontraktNr();
        this.docTyp = DocTypes.Abruf;

        // Path were file gone be stored
        this.destPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + abrufNr +
                "\\" + abrufNr + "_891.pdf");

        // Dir were file gone be stored
        this.destDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + abrufNr + "\\");

        // Parent Dir were file gone be stored
        this.destParentDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\");

        //  SampleDir (Folder which contains the structure an dir where file gone be stored)
        this.destSampleDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\Instandsetzungsaufträge\\HIL\\00_Rahmenvertrag Muster\\Abrufe\\00_Muster_Abruf");


        this.destArchivPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + "\\00_Archiv\\" + abrufNr + "\\");


        this.fileInDestPath = new File(destPath);
        this.destDir = new File(destDirPath);
        this.destParentDir = new File(destParentDirPath);
        this.destSampleDir = new File(destSampleDirPath);
        this.destArchivDir = new File(destArchivPath);

    }

    private int getKontraktNr() {
        AbrufAnalysis stringAnalyst = new AbrufAnalysis(docString);
        return stringAnalyst.getKontraktNr();
    }

    private long getAbrufNrFromDocString() {
        AbrufAnalysis stringAnalyst = new AbrufAnalysis(docString);
        return stringAnalyst.getAbrufNr();
    }
}