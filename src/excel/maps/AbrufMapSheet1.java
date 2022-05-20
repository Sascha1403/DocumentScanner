package excel.maps;

import document.analyse.abruf.RowAbrufSheet1;
import document.types.pdf.Pdf;
import document.types.pdf.PdfAbruf;
import tesseract.LoadData;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AbrufMapSheet1 {
    public java.util.Map<Integer, Object[]> map;


    public AbrufMapSheet1(List<Pdf> pdfAbrufe) throws Exception {
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

            RowAbrufSheet1 cellObjectArrLoader = new RowAbrufSheet1();
            Object[] cellObjArr = cellObjectArrLoader.getRowAbruf(docString);
            abrufMap.put(index, cellObjArr);
            index++;
        }
        return abrufMap;
    }

    public java.util.Map<Integer, Object[]> getMap() {
        return map;
    }

    public Long[] getAbrufNr() {
        Long[] AbrufNr = new Long[map.size()];
        Set<Integer> keyset = map.keySet();
        for (Integer key : keyset) {
            Object[] objArr = map.get(key);
            AbrufNr[key] = (Long) objArr[2];
        }
        return AbrufNr;
    }

    public Integer[] getKontraktNr() {
        Integer[] kontraktNr = new Integer[map.size()];
        Set<Integer> keyset = map.keySet();
        for (Integer key : keyset) {
            Object[] objArr = map.get(key);
            kontraktNr[key] = (Integer) objArr[3];
        }
        return kontraktNr;
    }

}
