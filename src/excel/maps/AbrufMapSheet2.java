package excel.maps;

import document.analyse.abruf.RowAbrufSheet1;
import document.analyse.abruf.RowAbrufSheet2;
import document.types.pdf.Pdf;
import document.types.pdf.PdfAbruf;
import tesseract.LoadData;

import java.io.File;
import java.util.List;
import java.util.TreeMap;

public class AbrufMapSheet2 {
    public java.util.Map<Integer, Object[]> map;


    public AbrufMapSheet2(List<Pdf> pdfAbrufe) throws Exception {
        this.map = getAbrufMap(pdfAbrufe);
    }

    private java.util.Map<Integer, Object[]> getAbrufMap(List<Pdf> pdfAbrufe) throws Exception {
        // Check if directory is empty
        if (pdfAbrufe == null) {
            throw new Exception("Kein Element Gefunden");
        }

        // Create an tree Map with all CellStrings from the files
        java.util.Map<Integer, Object[]> abrufMap = new TreeMap<Integer, Object[]>();

        // Get all cell String from the Abrufe an append them to the TreeMap
        int index = 0;
        for (Pdf abruf : pdfAbrufe) {
            String filePath = abruf.getAbsolutePath();
            String docString = abruf.getDocString();
            RowAbrufSheet2 cellObjectArrLoader = new RowAbrufSheet2();
            Object[] cellObjArr = cellObjectArrLoader.getRowAbruf(docString);
            abrufMap.put(index, cellObjArr);
            index++;
        }
        return abrufMap;
    }

    public java.util.Map<Integer, Object[]> getMap() {
        return map;
    }
}