package document.analyse.abruf;

// This class returns an object array. Each Element represents an cell of a row in excel Sheet
public class RowAbrufSheet1 {

    public Object[] getRowAbruf(String dataString) {

        AbrufAnalysis log = new AbrufAnalysis(dataString);
        String lieferTag = log.getDeliveryDay();
        Long abrufNr = log.getAbrufNr();
        int kontraktNr = log.getKontraktNr();
        String zufuefrungsNr = log.getZufuefrungsNr();
        int positionNr = log.getPositionNr();
        int menge = log.getMenge();
        String status = new String("Laufend");

        Object[] rowObjectArr = new Object[]{lieferTag, "", abrufNr, kontraktNr, "", zufuefrungsNr, "", positionNr, menge, status};

        return rowObjectArr;
    }


}
