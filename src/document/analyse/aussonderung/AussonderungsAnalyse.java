package document.analyse.aussonderung;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The Class gets all relevant Number/Information from the string representation of the Image
public class AussonderungsAnalyse {

    // docString comes from class tessarct.LoadData.getStringRepresentation
    String docString;

    public AussonderungsAnalyse(String docString) {
        this.docString = docString;
    }

    // returns the ZfiNr
    public Long getZfiNumber() {
        // All possible AbrufNr get stored in list
        List<Long> list = new ArrayList<Long>();

        // Find AbrufNr in docString
        Pattern p = Pattern.compile("(452[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m = p.matcher(docString);

        while (m.find()) {
            Long ZfiNr = Long.parseLong(m.group());  // possible ZfiNr in docString
            list.add(ZfiNr);           // store ZfiNr in docString
        }

        Long ZfiNr = list.get(0);  // take ZfiNr

        // Check if all detected ZfiNr are the same
        for (Long s : list) {
            if (!s.equals(list.get(0)))
                ZfiNr = 0L;
        }
        return ZfiNr;
    }

    // returns the IH Nr
    public Long getIhNr() {
        // All possible AbrufNr get stored in list
        List<Long> list = new ArrayList<Long>();

        // Find AbrufNr in docString
        Pattern p = Pattern.compile("(452[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m = p.matcher(docString);

        while (m.find()) {
            Long ZfiNr = Long.parseLong(m.group());  // possible ZfiNr in docString
            list.add(ZfiNr);           // store ZfiNr in docString
        }

        Long ZfiNr = list.get(0);  // take ZfiNr

        // Check if all detected ZfiNr are the same
        for (Long s : list) {
            if (!s.equals(list.get(0)))
                ZfiNr = 0L;
        }
        return ZfiNr;
    }


}
