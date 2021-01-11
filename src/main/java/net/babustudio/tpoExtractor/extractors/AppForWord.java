package net.babustudio.tpoExtractor.extractors;

import net.babustudio.tpoExtractor.models.Article;
import net.babustudio.tpoExtractor.models.DocxModel;

import java.io.File;
import java.io.IOException;

public class AppForWord extends App {

    @Override
    public void output() throws IOException {// to be continued...
        int counter = 1;
        for (Article article : this.articles) {
            if (!article.getTitle().isEmpty())
                if (article.getType() == 1)
                    new DocxModel(article).exportToWord(new File(this.outputDirectory + "/" + "[" + counter++
                            + "] " + article.getTitle() + ".docx"));
        }
    }
}
