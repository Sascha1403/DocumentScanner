package document.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocTypDetection {

    static public DocTypes[] getDocTypes(String[] docStringArr) throws Exception {
        DocTypes[] dataTypArr = new DocTypes[docStringArr.length];

        int index = 0;
        for (String s : docStringArr) {
            DocTypes dataTyp = getDataTyp(s);
            dataTypArr[index++] = dataTyp;
        }
        return dataTypArr;
    }

    protected static DocTypes getDataTyp(String docString) throws Exception {
        DocTypes dataTyp;


        if (checkAbrufTyp(docString)) {
            return dataTyp = DocTypes.Abruf;
        } else if (checkWeGlsMailTyp(docString)) {
            return dataTyp = DocTypes.WeGlsMail;
        } else if (checkWeHIL(docString)) {
            return dataTyp = DocTypes.WeHIL;
        } else if (checkMeldungInst(docString)) {
            return dataTyp = DocTypes.MeldungInst;
        } else {
            System.out.println("Kein Datentyp gefunden");
            return dataTyp = DocTypes.UnknownDocument;
        }
    }

    protected static Boolean checkAbrufTyp(String docString) {
        // Elements in Abruf
        Pattern pAbruf1 = Pattern.compile("Leistungsart");
        Matcher m1 = pAbruf1.matcher(docString);

        Pattern pAbruf2 = Pattern.compile("@hilgmbh.de");
        Matcher m2 = pAbruf2.matcher(docString);

        Pattern pAbruf3 = Pattern.compile("Partner der Bundeswehr");
        Matcher mAbruf3 = pAbruf3.matcher(docString);

        Pattern pAbruf4 = Pattern.compile("Position");
        Matcher mAbruf4 = pAbruf4.matcher(docString);


        if (m1.find() && m2.find() && mAbruf3.find() && mAbruf4.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected static Boolean checkWeGlsMailTyp(String docString) {
        // Elements in Abruf
        Pattern p1 = Pattern.compile("Global Logistics Support GmbH");
        Matcher m1 = p1.matcher(docString);

        Pattern p2 = Pattern.compile("Parkring 13");
        Matcher m2 = p2.matcher(docString);

        Pattern p3 = Pattern.compile("(460[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m3 = p3.matcher(docString);

        Pattern p4 = Pattern.compile("WE-Meldung");
        Matcher m4 = p4.matcher(docString);

        Pattern p5 = Pattern.compile("Wareneingangsmeldung");
        Matcher m5 = p5.matcher(docString);

        Pattern p6 = Pattern.compile("WE Meldung");
        Matcher m6 = p6.matcher(docString);

        Pattern p7 = Pattern.compile("Sascha Franz");
        Matcher m7 = p7.matcher(docString);

        if (m1.find() && m2.find() && m3.find() && (m4.find() || m5.find() || m6.find()) && !m7.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected static Boolean checkWeHIL(String docString) {
        // Elements in Abruf
        Pattern p1 = Pattern.compile("SASPF");
        Matcher m1 = p1.matcher(docString);

        Pattern p2 = Pattern.compile("Heeresinstandsetzungslogistik");
        Matcher m2 = p2.matcher(docString);

        Pattern p3 = Pattern.compile("(460[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m3 = p3.matcher(docString);

        Pattern p4 = Pattern.compile("WE-Meldung");
        Matcher m4 = p4.matcher(docString);

        Pattern p5 = Pattern.compile("Wareneingangsmeldung");
        Matcher m5 = p5.matcher(docString);

        Pattern p6 = Pattern.compile("WE Meldung");
        Matcher m6 = p6.matcher(docString);

        Pattern p7 = Pattern.compile("Wareneingang");
        Matcher m7 = p7.matcher(docString);

        if (m1.find() && m2.find() && m3.find() && (m4.find() || m5.find() || m6.find() || m7.find())) {
            return true;
        } else {
            return false;
        }
    }

    protected static Boolean checkMeldungInst(String docString) {
        // Elements in Abruf
        Pattern p1 = Pattern.compile("LuF");
        Matcher m1 = p1.matcher(docString);

        Pattern p2 = Pattern.compile("Freigabe");
        Matcher m2 = p2.matcher(docString);

        Pattern p3 = Pattern.compile("(460[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m3 = p3.matcher(docString);

        Pattern p4 = Pattern.compile("Parkring 13");
        Matcher m4 = p4.matcher(docString);

        Pattern p5 = Pattern.compile("CoC");
        Matcher m5 = p5.matcher(docString);

        Pattern p6 = Pattern.compile("Meldung");
        Matcher m6 = p6.matcher(docString);

        Pattern p7 = Pattern.compile("Inst");
        Matcher m7 = p7.matcher(docString);

        if (m1.find() && m2.find() && m3.find() && m4.find() && m5.find() && m6.find() && m7.find()) {
            return true;
        } else {
            return false;
        }
    }
}
