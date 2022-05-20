package excel;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelSheetDemoClass {
    public static void main(String[] args) throws Exception {

        // Create a file object
        // for the path of existing Excel file
        // Give the path of the file as parameter
        // from where file is to be read
        File file = new File("excelSheet/testSheet.xlsx");

        // Create a FileInputStream object
        // for getting the information of the file
        FileInputStream fip = new FileInputStream(file);

        // Getting the workbook instance for XLSX file1
        XSSFWorkbook workbook = new XSSFWorkbook(fip);

        fip.close();

        // Ensure if file exist or not
        if (file.isFile() && file.exists()) {
            System.out.println("Geeks.xlsx open");
        } else {
            System.out.println("Geeks.xlsx either not exist"
                    + " or can't open");
        }

        // Get first/desired sheet from workbook
        XSSFSheet sheet = workbook.getSheetAt(0);


        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Test", "Kumar"});


        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = sheet.getLastRowNum()+1;
        for (String key : keyset) {

            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }


        try {
            // this actually Writes the workbook
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            System.out.println("testSheet.xlsx written successfully on disk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



