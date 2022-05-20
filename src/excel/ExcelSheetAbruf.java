package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ExcelSheetAbruf {
    private static String path;
    private File file;
    public static FileInputStream fip;
    private static XSSFWorkbook workbook;


    public ExcelSheetAbruf(String path) throws Exception {
        this.path = path;


        file = new File(path);
        // Create a FileInputStream object
        // for getting the information of the file
        fip = new FileInputStream(file);

        // Getting the workbook instance for XLSX file1
        workbook = new XSSFWorkbook(fip);

        // Ensure if file exist or not
        if (!file.isFile() || !file.exists()) {
            throw new NoSuchElementException(path + "either not exist or can't open");
        }
    }

    public void writeData(Map<Integer, Object[]> map, int sheetNumber, int indexControlRow) {
        // Get first/desired sheet from workbook
        XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

        // Iterate over data and write to sheet
        Set<Integer> keyset = map.keySet();

        // Get last not empty row
        int rowIndex = sheet.getLastRowNum();



        for (int rowNum = sheet.getLastRowNum(); rowNum >= 0; rowNum--) {
            Row row = sheet.getRow(rowNum);

            // Check if cell Nr 3(index 2) is Numeric (this cell is in Sheet1 and Sheet2 never empty)
            if (row.getCell(indexControlRow).getCellType() == CellType.NUMERIC) {
                rowIndex = rowNum + 1;
                break;
            }
        }


        for (Integer key : keyset) {
            int cellnum = 0;
            Row row = sheet.getRow(rowIndex++);


            // this creates a new row in the sheet

            Object[] objArr = map.get(key);
            for (Object obj : objArr) {
                Cell cell = row.getCell(cellnum++);



                // this line creates a cell in the next column of that row
                if (obj instanceof String)
                    cell.setCellValue((String) obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);

                else if (obj instanceof Long)
                    cell.setCellValue((Long) obj);

                else if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
            }
        }
        try {
            // this actually Writes the workbook
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println(path + "written successfully on disk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

