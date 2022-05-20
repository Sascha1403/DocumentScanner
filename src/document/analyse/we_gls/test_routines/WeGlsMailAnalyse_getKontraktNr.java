package document.analyse.we_gls.test_routines;

import document.analyse.abruf.AbrufAnalysis;
import document.analyse.we_gls.WeGlsMailAnalyse;
import tesseract.LoadData;

import java.io.IOException;

public class WeGlsMailAnalyse_getKontraktNr {
    public static void main(String[] args) throws IOException {
        String dataPath = new String("images/mergedPdf16.pdf");
        String dataString;

        LoadData DataLoader = new LoadData(dataPath);
        dataString = DataLoader.getStringRepresentation();

        WeGlsMailAnalyse log = new WeGlsMailAnalyse(dataString);
        int kontraktNr = log.getKontraktNr();
        System.out.println(kontraktNr);
    }
}
