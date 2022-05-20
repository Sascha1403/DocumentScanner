package document.types.pdf;

import document.analyse.abruf.AbrufAnalysis;
import document.types.DocTypes;

import java.io.File;

public class PdfAussonderung extends Pdf {
    double IhNr;
    double ZfiNr;
    int AussonderungsNr;

    public PdfAussonderung(String path, String docString) {
        super(path);
        this.docString = docString;
        this.IhNr = getIhNr();
        //this.kontraktNr = getZfiNr();
        this.docTyp = DocTypes.Aussonderung;

        // Path were file gone be stored
        this.destPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\MTU-Triebwerk\\02 - Aussonderungen\\2022\\" +
                AussonderungsNr + "\\ -2022");

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

    private int getIhNr() {
        AbrufAnalysis stringAnalyst = new AbrufAnalysis(docString);
        return stringAnalyst.getKontraktNr();
    }

    private long getZfiNr() {
        AbrufAnalysis stringAnalyst = new AbrufAnalysis(docString);
        return stringAnalyst.getAbrufNr();
    }
}

