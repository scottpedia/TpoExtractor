package net.babustudio.tpoExtractor.extractors;

import net.babustudio.tpoExtractor.models.Article;
import net.babustudio.tpoExtractor.models.DocxModel;

import java.io.File;
import java.io.IOException;

public class AppForWord extends App {

    @Override
    public void output() throws IOException {//to be continued...
        for (Article article : this.articles) {
            if (!article.getTitle().isEmpty())
                new DocxModel(article).exportToWord(new File(this.outputDirectory + "/" + "[" + article.articleID + "] " + article.getTitle() + ".docx"));
        }
    }
}
