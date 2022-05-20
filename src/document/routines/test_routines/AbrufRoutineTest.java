package document.routines.test_routines;

import document.types.pdf.Pdf;
import document.types.pdf.PdfAbruf;
import excel.ExcelSheetAbruf;
import excel.maps.AbrufMapSheet1;
import excel.maps.AbrufMapSheet2;

import java.util.List;

public class AbrufRoutineTest {
    private List<Pdf> abrufFiles;
    String excelPath = new String("excelSheet/testSheet.xlsx");
    int indexExcelSheet1 = 1;
    int indexExcelSheet2 = 2;
    int indexControlRow1 = 2;
    int indexControlRow2 = 0;


    public AbrufRoutineTest(List<Pdf> abrufFiles) {
        this.abrufFiles = abrufFiles;
    }

    public void executeRoutine() throws Exception {
        if (abrufFiles.isEmpty())
            return;

        AbrufMapSheet1 classAbrufMapSheet1 = new AbrufMapSheet1(abrufFiles);
        AbrufMapSheet2 classAbrufMapSheet2 = new AbrufMapSheet2(abrufFiles);
        java.util.Map<Integer, Object[]> abrufMapSheet1 = classAbrufMapSheet1.getMap();
        java.util.Map<Integer, Object[]> abrufMapSheet2 = classAbrufMapSheet2.getMap();

        // Append abrufe to the excel sheet
        ExcelSheetAbruf excelSheetAbruf = new ExcelSheetAbruf(excelPath);
        excelSheetAbruf.writeData(abrufMapSheet1, indexExcelSheet1, indexControlRow1);
        excelSheetAbruf.writeData(abrufMapSheet2, indexExcelSheet2, indexControlRow2);

        for (Pdf abrufFile : abrufFiles) {
            abrufFile.copyInDestPath();
        }
    }
}
