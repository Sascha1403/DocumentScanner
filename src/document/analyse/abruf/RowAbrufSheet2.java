package document.analyse.abruf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RowAbrufSheet2 {
    public Object[] getRowAbruf(String dataString) {

        AbrufAnalysis log = new AbrufAnalysis(dataString);
        String date = log.getDeliveryDay();
        Date lieferTag = parseDate(date);;
        Long abrufNr = log.getAbrufNr();
        int kontraktNr = log.getKontraktNr();
        String status = new String("Laufend");

        Object[] rowObjectArr = new Object[]{abrufNr, kontraktNr, lieferTag, "", "", "", "", "", "", "", "", "", "", "", "", "",status};

        return rowObjectArr;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}

