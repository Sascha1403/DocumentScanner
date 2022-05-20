package tesseract;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class LoadData {
    String pathData;

    public LoadData(String pathData) {
        this.pathData = pathData;
    }

    public String getStringRepresentation() {


        String result = "";

        File imageFile = new File(pathData);
        Tesseract instance = new Tesseract();


        try {
            result = instance.doOCR(imageFile);
            result.toString();


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return result;
    }


}