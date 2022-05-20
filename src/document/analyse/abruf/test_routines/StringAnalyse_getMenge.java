package document.analyse.abruf.test_routines;

import document.analyse.abruf.AbrufAnalysis;
import tesseract.LoadData;

public class StringAnalyse_getMenge {
    public static void main(String[] args) {
        String dataPath = new String("images/4601522650_891.pdf");
        String dataString;

        LoadData DataLoader = new LoadData(dataPath);
        dataString = DataLoader.getStringRepresentation();

        AbrufAnalysis log = new AbrufAnalysis(dataString);
        int menge = log.getMenge();

        System.out.println(menge);
    }
}
