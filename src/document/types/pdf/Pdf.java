package document.types.pdf;


import document.types.DocTypes;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Pdf extends File {
    protected String docString;
    protected int kontraktNr;
    protected long abrufNr;
    protected String destPath;
    protected String destDirPath;
    protected String destParentDirPath;
    protected String destSampleDirPath;
    protected String columnNameInExcel;
    protected String excelEntry;
    protected DocTypes docTyp;
    protected String destUnsortablePath;
    protected String destArchivPath;

    protected File fileInDestPath;
    protected File destDir;
    protected File destParentDir;
    protected File destSampleDir;
    protected File destUnsortableDir;
    protected File destArchivDir;



    public Pdf(String pathDoc) {
        super(pathDoc);
        this.destUnsortablePath = new String ("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\" +
                "Instandsetzungsaufträge\\HIL\\00_Allgemeine Dokumente\\Scan Sorter Problems\\");


    }

    public String getDocString() {
        return docString;
    }

    public DocTypes getDocTyp() {
        return docTyp;
    }


    public void copyInDestPath() throws Exception {

        //Check if file already exist in DestPath
        if (fileInDestPath.exists()||destArchivDir.exists()) {
            if (fileInDestPath.exists())
                System.out.println("\nDokument existieriert schon auf Fileserver, deswegen nicht abgelegt"+  "\nDokument: " + this.getName() +
                        "\nDokumententyp: " + this.docTyp + "\nAbrufnummer: " + abrufNr + "\nKontraktNr: " + kontraktNr);
            else if (fileInDestPath.exists() & destArchivDir.exists())
                System.out.println("\n!!! Ordner existiert im Archiv und unter Abrufe !!! " +  "\nDokument: " + this.getName() +
                        "\nDokumententyp: " + this.docTyp + "\nAbrufnummer: " + abrufNr + "\nKontraktNr: " + kontraktNr);
            else
                System.out.println("\nArchiv existieriert schon auf Fileserver, deswegen nicht abgelegt" +  "\nDokument: " + this.getName() +
                    "\nDokumententyp: " + this.docTyp + "\nAbrufnummer: " + abrufNr + "\nKontraktNr: " + kontraktNr);

            return;
        }


        // If destDir exist copy file in DestPath
        if (destDir.exists()) {
            FileUtils.copyFile(this, new File(destPath));
            System.out.println("\nErfolgreich abgelegt" + "\nDokument: " + this.getName() +
                    "\nDokumententyp: " + this.docTyp + "\nAbrufnummer: " + abrufNr + "\nKontraktNr: " + kontraktNr);
            return;
        }

        // If ParentDir exist create destDir and copy file in destDir
        if (destParentDir.exists()) {
            createDestDir();
            FileUtils.copyFile(this, new File(destPath));
            System.out.println("\nErfolgreich abgelegt" + "\nDokument: " + this.getName() +
                    "\nDokumententyp: " + this.docTyp + "\nAbrufnummer: " + abrufNr + "\nKontraktNr: " + kontraktNr);
            return;
        } else {

            System.out.println("Es existiert der Ordner " + destParentDir + " nicht. \nDer File " +
                    "" + this.getName() + " kann nicht in Ordnerstruktur abgelegt werden");
        }
    }

    public void createDestDir() throws IOException {
        FileUtils.copyDirectory(destSampleDir, new File(destDirPath));
    }
}
