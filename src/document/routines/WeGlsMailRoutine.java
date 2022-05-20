package document.routines;

import document.types.pdf.Pdf;
import document.types.pdf.PdfAbruf;
import document.types.pdf.PdfWeGlsMail;
import excel.maps.AbrufMapSheet1;

import java.util.List;

public class WeGlsMailRoutine extends Routine {
    private List<Pdf> WeGlsMailFiles;
    String excelPath = new String("excelSheet/testSheet.xlsx");
    int indexExcelSheet1 = 1;

    public WeGlsMailRoutine(List<Pdf> WeGlsMailFiles) {
        super(WeGlsMailFiles);
        this.WeGlsMailFiles = WeGlsMailFiles;
    }
}
