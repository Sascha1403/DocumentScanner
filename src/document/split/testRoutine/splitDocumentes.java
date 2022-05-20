package document.split.testRoutine;

import document.split.DocumentSpliter;

import java.io.IOException;

public class splitDocumentes {
    public static void main(String[] args) throws Exception {
        String fileDirPath = new String("images");
        DocumentSpliter documentSpliter = new DocumentSpliter(fileDirPath);
        documentSpliter.splitDocuments();
        documentSpliter.mergeDocuments();
    }
}
