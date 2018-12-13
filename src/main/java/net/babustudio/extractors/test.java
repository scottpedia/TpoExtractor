package net.babustudio.extractors;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        XWPFDocument docx = new XWPFDocument();
        XWPFParagraph paragraph = docx.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setFontSize(72);
        run.setText("Hello Wor(l)d!");
        try{
            docx.write(new FileOutputStream("/Users/Billy/Documents/coda/hop.docx"));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                docx.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
