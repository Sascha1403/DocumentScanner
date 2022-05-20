package document.types.pdf;

import document.analyse.we_gls.WeGlsMailAnalyse;
import document.types.DocTypes;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PdfWeHIL extends Pdf{
    public PdfWeHIL(String path, String docString) throws IOException {
        super(path);
        this.docString = docString;
        this.abrufNr = getabrufNr();
        this.kontraktNr = getKontraktNr();
        this.docTyp = DocTypes.WeHIL;

        // Path were file gone be stored
        this.destPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + abrufNr +
                "\\WE_Hil.pdf");

        // Dir were file gone be stored
        this.destDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + abrufNr +"\\");

        // Parent Dir were file gone be stored
        this.destParentDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\");

        //  SampleDir (Folder which contains the structure an dir where file gone be stored)
        this.destSampleDirPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\Instandsetzungsaufträge\\HIL\\00_Rahmenvertrag Muster\\Abrufe\\00_Muster_Abruf");

        // Archiv Path
        this.destArchivPath = new String("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\" + kontraktNr + "\\Abrufe\\" + "\\00_Archiv\\" +abrufNr + "\\");


        this.fileInDestPath = new File(destPath);
        this.destDir = new File(destDirPath);
        this.destParentDir = new File(destParentDirPath);
        this.destSampleDir = new File(destSampleDirPath);
        this.destArchivDir = new File(destArchivPath);
    }

    private int getKontraktNr() throws IOException {
        WeGlsMailAnalyse stringAnalyst = new WeGlsMailAnalyse(docString);
        return stringAnalyst.getKontraktNr();
    }

    private long getabrufNr(){
        WeGlsMailAnalyse stringAnalyst = new WeGlsMailAnalyse(docString);
        return stringAnalyst.getAbrufNr();
    }
}


