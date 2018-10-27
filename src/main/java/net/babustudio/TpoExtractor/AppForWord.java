package net.babustudio.TpoExtractor;

import net.babustudio.Models.Article;
import net.babustudio.Models.DocxModel;

import java.io.File;
import java.io.IOException;

public class AppForWord extends App {

    @Override
    public void output() throws IOException {//to be continued...
        for (Article article : this.articles) {
            new DocxModel(article).exportToWord(new File(this.outputDirectory + "/" + "[" + article.articleID + "] " + article.getTitle() + ".docx"));
        }
    }
}
