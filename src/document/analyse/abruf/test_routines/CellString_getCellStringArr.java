package document.analyse.abruf.test_routines;

import document.analyse.abruf.RowAbrufSheet1;
import tesseract.LoadData;

public class CellString_getCellStringArr {
    public static void main(String[] args) {
        String dataPath = new String("images/4601476457_891.pdf");
        String dataString;

        LoadData DataLoader = new LoadData(dataPath);
        dataString = DataLoader.getStringRepresentation();

        RowAbrufSheet1 cellObjectArrLoader = new RowAbrufSheet1();
        Object[] cellObjArr = cellObjectArrLoader.getRowAbruf(dataString);
        System.out.println(cellObjArr);
    }
}
