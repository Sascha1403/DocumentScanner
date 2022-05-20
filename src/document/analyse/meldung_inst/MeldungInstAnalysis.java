package document.analyse.meldung_inst;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeldungInstAnalysis {

    // dataString comes from class tessarct.LoadData.getStringRepresentation
    String dataString;

    public MeldungInstAnalysis(String dataString) {
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
    public int getKontraktNr() throws IOException {
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
                return kontraktNr;
            }
        }

        /* If AbrufNr is 0 search in HIL folder */
        if (kontraktNr == 0) {


            Long abrufNr = getAbrufNr();

            // Search Criterion
            Pattern p = Pattern.compile("(300[0-9][0-9][0-9][0-9])"); // Search criterion for Kontrakt folder
            Pattern p2 = Pattern.compile(String.valueOf(abrufNr)); // Search criterion for abrufNr

            // In HIL Folder all KontraktFolder are included
            File folder = new File("I:\\AIN\\BAAINBw-ZtQ401\\PG100\\17-Projekte\\GLS V2.0\\Instandsetzungsaufträge\\HIL");
            File[] listOfFiles = folder.listFiles();

            // Iterate over HIL Folder an get Kontrakt Folder
            for (File file : listOfFiles) {
                String datapath = file.getCanonicalPath().toString();
                Matcher m = p.matcher(datapath);

                // If folder is KontraktFolder search in Kontrakt Folder for AbrufFolder
                if (m.find()) {
                    String datapathAbrufFolder = new String(datapath + "\\Abrufe");
                    File abrufFolder = new File(datapathAbrufFolder);
                    File[] listofAbrufe = abrufFolder.listFiles();

                    // If Abruf is in Kontrakt Folder return Kontrakt Nr.
                    for (File abruf : listofAbrufe) {
                        String abrufpath = abruf.getCanonicalPath().toString();
                        Matcher m2 = p2.matcher(abrufpath);

                        if (m2.find()) {
                            kontraktNr = Integer.parseInt(m.group()); // m.groud is the Kontrakt Nr.
                            return kontraktNr;
                        }
                    }
                }
            }
        }
        // !!! Wenn Nummer nicht gefunden dann muss in Excel Sheet geschaut werden Muss noch Implementiert werden !!!
        return kontraktNr;
    }
}






