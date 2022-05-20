package document.routines.test_routines;

import document.types.pdf.Pdf;

import java.util.List;

public class WeHILRoutine {
    private List<Pdf> WeHil_Files;
    String excelPath = new String("excelSheet/testSheet.xlsx");
    int indexExcelSheet1 = 1;

    public WeHILRoutine(List<Pdf> WeHil_Files) {
        this.WeHil_Files = WeHil_Files;
    }

    public void execute() throws Exception {
        // Abfrage ob WeGLS empty
        if (WeHil_Files.isEmpty())
            return;

        for (Pdf weHilMail: WeHil_Files) {

            weHilMail.copyInDestPath();
        }
        System.out.println("WE_GLS saved on Fileserver");
    }
}
