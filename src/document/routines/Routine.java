package document.routines;

import document.routines.test_routines.AbrufRoutineTest;
import document.types.DocTypes;
import document.types.pdf.Pdf;
import excel.ExcelSheetAbruf;
import excel.maps.AbrufMapSheet1;
import excel.maps.AbrufMapSheet2;

import java.util.List;

public abstract class Routine {
    private List<Pdf> listFiles;

    public Routine (List<Pdf> listFiles) {
        this.listFiles = listFiles;
    }

    public void execute() throws Exception {
        // Abfrage ob Liste leer
        if (listFiles.isEmpty())
            return;

        // Kopiere Files auf Server
        copyFilesInPath();
    }

    private void copyFilesInPath () throws Exception {
        for (Pdf file: listFiles) {
            file.copyInDestPath();
        }
    }



}
