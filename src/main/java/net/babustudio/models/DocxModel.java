package net.babustudio.models;

import net.babustudio.Util;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class DocxModel extends Article {

    XWPFDocument document = null;
    private int titleSize = 30;
    private int bodySize = 12;
    private String titleFont = "Times New Roman";
    private boolean isTitleBold = true;
    private String bodyFont = "Times New Roman";
    private String headerBody = "TOEFL Preparation Resources";
    private String headerFont = "Menlo";

    public DocxModel() {
        super();
    }

    public DocxModel(Article article) {
        super();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.articleID = article.getArticleID();
    }

    private XWPFDocument output() {
        this.document = new XWPFDocument();

        XWPFHeader header = this.document.createHeader(HeaderFooterType.DEFAULT);
        XWPFRun headerRun = header.createParagraph().createRun();
        headerRun.setFontFamily(this.headerFont);
        headerRun.setText(this.headerBody);

        XWPFParagraph title = this.document.createParagraph();
        XWPFRun titleRun = title.createRun();
        titleRun.setFontSize(this.titleSize);
        titleRun.setBold(this.isTitleBold);
        titleRun.setFontFamily(this.titleFont);
        titleRun.setText(this.title + "\n\n");

        XWPFParagraph body = this.document.createParagraph();
        XWPFRun bodyRun = body.createRun();
        bodyRun.setFontFamily(this.bodyFont);
        bodyRun.setFontSize(this.bodySize);
        bodyRun.setText(Util.replace(this.content));

        return this.document;
    }

    public void exportToWord(File file) throws IOException {
        file.createNewFile();
        this.output().write(new FileOutputStream(file));
    }

}
