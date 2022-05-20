package document.analyse.we_gls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The Class gets all relevant Number/Information from the string representation of the Image
public class WeGlsMailAnalyse {
    // docString comes from class tessarct.LoadData.getStringRepresentation
    String docString;

    public WeGlsMailAnalyse(String docString) {
        this.docString = docString;
    }

    // returns the AbrufNr
    public Long getAbrufNr() {
        // All possible AbrufNr get stored in list
        List<Long> list = new ArrayList<Long>();

        // Find AbrufNr in docString
        Pattern p = Pattern.compile("(460[0-9][0-9][0-9][0-9][0-9][0-9][0-9])");
        Matcher m = p.matcher(docString);

        while (m.find()) {
            Long AbrufNr = Long.parseLong(m.group());  // possible AbrufNr in docString
            list.add(AbrufNr);           // store AbrufNr in docString
        }

        Long AbrufNr = list.get(0);  // take AbrufNr

        // Check if all detected AbrufNr are the same
        for (Long s : list) {
            if (!s.equals(list.get(0)))
                AbrufNr = 0L;
        }
        return AbrufNr;
    }

    /* This Method search in all Kontrakt Folder for the AbrufNr. If AbrufNr is found in Kontrakt folder it
       returns the KontraktNr */
    public int getKontraktNr() throws IOException {

        int kontraktNr = 0;
        Long abrufNr = getAbrufNr();

        String[] lines = docString.split("\n");


        /* Check if KontraktNr is in Mail */
        for (String s : lines) {
            Pattern p = Pattern.compile("(300[0-9][0-9][0-9][0-9])");
            Matcher m = p.matcher(s);


            // If KontraktNr and the word Kontrakt is fond in one line set kontraktNr
            if (m.find()) {
                kontraktNr = Integer.parseInt(m.group());
                return kontraktNr;
            }
        }


        /* If Kontrakt Nr is not in Mail Check in Folder Structur */
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

                String datapathAbrufFolderArchiv = new String(datapath + "\\Abrufe\\00_Archiv");
                File abrufFolderArchiv = new File(datapathAbrufFolderArchiv);
                File[] listofAbrufeArchiv = abrufFolderArchiv.listFiles();

                // If Abruf is in Kontrakt FolderArchiv return Kontrakt Nr.
                for (File abruf : listofAbrufeArchiv) {
                    String abrufpath = abruf.getCanonicalPath().toString();
                    Matcher m2 = p2.matcher(abrufpath);

                    if (m2.find()) {
                        kontraktNr = Integer.parseInt(m.group()); // m.groud is the Kontrakt Nr.
                        return kontraktNr;
                    }
                }
            }
        }


        // !!! Wenn Nummer nicht gefunden dann muss in Excel Sheet geschaut werden Muss noch Implementiert werden !!!
        return kontraktNr;
    }



}
