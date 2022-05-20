package Test;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AbstactClass {
    public static void main(String args[]) throws IOException {
        PDDocument doc = PDDocument.load(new File("images/Aussonder_II.pdf"));
        String text = new PDFTextStripper().getText(doc);
    }
}

