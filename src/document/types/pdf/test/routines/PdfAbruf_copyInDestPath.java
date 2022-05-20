package document.types.pdf.test.routines;

import document.types.pdf.PdfAbruf;
import tesseract.LoadData;

import java.io.File;

public class PdfAbruf_copyInDestPath {
    public static void main(String[] args) throws Exception {
        // Abruf which an existing parentDir and an none existing destDir
        LoadData DataLoader = new LoadData("\"images/4601519617_891.pdf");
        String docstring = DataLoader.getStringRepresentation();
        PdfAbruf abrufDoc3 = new PdfAbruf("images/4601519617_891.pdf", docstring);
        abrufDoc3.copyInDestPath();

        // Zur Überprüfung muss der Ordner Abruf entfernt werden ggf. sogar neuen Abruf verwenden
    }
}
