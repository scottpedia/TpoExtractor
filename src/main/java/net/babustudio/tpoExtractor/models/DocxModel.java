package net.babustudio.tpoExtractor.models;

import net.babustudio.tpoExtractor.utils.Utils;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class DocxModel extends Article {

    private XWPFDocument document = null;

// --Commented out by Inspection START (2019/1/6, 10:15):
//    public DocxModel() {
//        super();
//    }
// --Commented out by Inspection STOP (2019/1/6, 10:15)

    public DocxModel(Article article) {
        super();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.articleID = article.getArticleID();
        this.type = article.type;
    }

    private XWPFDocument output() {
        this.document = new XWPFDocument();

        XWPFHeader header = this.document.createHeader(HeaderFooterType.DEFAULT);
        XWPFRun headerRun = header.createParagraph().createRun();
        String headerFont = "Menlo";
        headerRun.setFontFamily(headerFont);
        String headerBody = "TOEFL Preparation Resources";
        headerRun.setText(headerBody);

        XWPFParagraph title = this.document.createParagraph();
        XWPFRun titleRun = title.createRun();
        int titleSize = 30;
        titleRun.setFontSize(titleSize);
        boolean isTitleBold = true;
        titleRun.setBold(isTitleBold);
        String titleFont = "Times New Roman";
        titleRun.setFontFamily(titleFont);
        titleRun.setText(this.title + "\n\n");

        XWPFParagraph body = this.document.createParagraph();
        XWPFRun bodyRun = body.createRun();
        String bodyFont = "Times New Roman";
        bodyRun.setFontFamily(bodyFont);
        int bodySize = 12;
        bodyRun.setFontSize(bodySize);
        bodyRun.setText(Utils.replace(this.content));

        return this.document;
    }

    public void exportToWord(File file) throws IOException {
        file.createNewFile();
        this.output().write(new FileOutputStream(file));
    }

}
