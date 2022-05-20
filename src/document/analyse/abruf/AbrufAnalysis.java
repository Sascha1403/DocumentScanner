package document.analyse.abruf;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The Class gets all relevant Number/Information from the string representation of the Image
public class AbrufAnalysis {

    // dataString comes from class tessarct.LoadData.getStringRepresentation
    String dataString;

    public AbrufAnalysis(String dataString) {
        this.dataString = dataString;
    }

    // returns the AbrufNr
    public Long getAbrufNr() {
        // All possible AbrufNr get stored in list
        List<Long> list = new ArrayList<Long>();

        // Find AbrufNr in dataString
        Pattern p = Pattern.compile("(460[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m = p.matcher(dataString);

        while (m.find()) {
            Long AbrufNr = Long.parseLong(m.group());  // possible AbrufNr in dataString
            list.add(AbrufNr);           // store AbrufNr in dataString
        }

        Long AbrufNr = list.get(0);  // take AbrufNr

        // Check if all detected AbrufNr are the same
        for (Long s : list) {
            if (!s.equals(list.get(0)))
                AbrufNr = 0L;
        }
        return AbrufNr;
    }

    // returns KontraktNr
    public int getKontraktNr() {
        // Split dataString in lines
        String[] lines = dataString.split("\n");

        // Set KontraktNr to not fond
        int kontraktNr = 0;

        // Check if KontraktNr and the word Kontrakt is find in one line
        for (String s : lines) {


            Pattern p = Pattern.compile("(300[0-9][0-9][0-9][0-9])");
            Pattern p2 = Pattern.compile("Kontrakt");

            Matcher m = p.matcher(s);
            Matcher m2 = p2.matcher(s);

            // If KontraktNr and the word Kontrakt is fond in one line set kontraktNr
            if (m.find() & m2.find()) {
                kontraktNr = Integer.parseInt(m.group());
            }
        }
        return kontraktNr;
    }

    // returns PositionNr
    public int getPositionNr() {

        // Split dataString in lines
        String[] lines = dataString.split("\n");

        // Set PositionNr to not fond
        int positionNr = 0;

        // Check if positionNr and the word Kontrakt is find in one line
        for (String s : lines) {


            Pattern p = Pattern.compile("(000[0-9][0])");
            Pattern p2 = Pattern.compile("Position");

            Matcher m = p.matcher(dataString);
            Matcher m2 = p2.matcher(dataString);

            // If positionNr and the word Position is fond in one line set positionNr
            while (m.find() & m2.find()) {
                positionNr = Integer.parseInt(m.group());
            }
        }
        return positionNr;
    }

    // get ZuführungsNr
    public String getZufuefrungsNr(){

        // Split dataString in lines
        String[] lines = dataString.split("\n");

        // Set PositionNr to not fond
        String zufuehrNr = new String("Keine ZuführNr gefunden");

        // Check if positionNr and the word Kontrakt is find in one line
        for (String s : lines) {


            Pattern p = Pattern.compile("Zuf");
            Pattern p2 = Pattern.compile("hrNr.");


            Matcher m = p.matcher(s);
            Matcher m2 = p.matcher(s);


            // If positionNr and the word Position is fond in one line set positionNr
            if (m.find() & m2.find()) {
                String[] stringObject = s.split(" ");
                zufuehrNr = stringObject[stringObject.length-1];
            }
        }
        return zufuehrNr;

    }

    // get Menge
    public int getMenge(){

        // Split dataString in lines
        String[] lines = dataString.split("\n");

        // Set PositionNr to not fond
        int menge = 0;

        // Check if positionNr and the word Kontrakt is find in one line
        for (String s : lines) {


            Pattern p = Pattern.compile(" LE ");
            Pattern p2 = Pattern.compile("([0-100])");


            Matcher m = p.matcher(s);
            Matcher m2 = p.matcher(s);


            // If positionNr and the word Position is fond in one line set positionNr
            if (m.find() & m2.find()) {
                String[] stringObject = s.split(" ");
                menge = Integer.parseInt(stringObject[0]);
            }
        }
        return menge;

    }

    // get Liefertermin
    public String getDeliveryDay(){

        // Split dataString in lines
        String[] lines = dataString.split("\n");

        // Set PositionNr to not fond
        String deliveryDay = new String("Keine Liefertermin gefunden");

        // Check if positionNr and the word Kontrakt is find in one line
        for (String s : lines) {


            Pattern p = Pattern.compile("Liefertermin");
            Pattern p2 = Pattern.compile("([2021-2030])");


            Matcher m = p.matcher(s);
            Matcher m2 = p.matcher(s);


            // If positionNr and the word Position is fond in one line set positionNr
            if (m.find() & m2.find()) {
                String[] stringObject = s.split(" ");
                deliveryDay = stringObject[stringObject.length -1];
            }
        }
        return deliveryDay;

    }
}
