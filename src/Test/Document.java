package Test;

import document.types.pdf.PdfAbruf;

import java.util.ArrayList;
import java.util.List;

abstract class  Document {
    protected String path;

    protected String getPathA() {
        return path;
    }

    public String getPathB(String path) {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
    }
}

class DocumentA extends Document{
    DocumentA () {
        this.path = "pathA";
    }
}

class DocumentB extends Document{
    String path = "pathB";

    public String getPathB(){
        return super.getPathB(path);
    }
}

class test {
    public static void main(String[] args) {
        DocumentA documentA = new DocumentA();
        DocumentB documentB = new DocumentB();
        System.out.println(documentA.getPathA());
        System.out.println(documentB.getPathB());
    }
}