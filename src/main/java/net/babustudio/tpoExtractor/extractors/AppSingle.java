package net.babustudio.tpoExtractor.extractors;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import net.babustudio.tpoExtractor.models.Article;

public class AppSingle extends App {

    @Override
    public void output() throws IOException {
        File dir = new File(this.outputDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException("Failed to access the directory.");
        }
        dir.createNewFile();
        File mapping = new File(this.outputDirectory + "/" + "[" + "ULTIMATE_COLLECTION" + "].txt");
        mapping.createNewFile();
        for (Article article : this.articles) {
            FileWriter fileWriter = new FileWriter(mapping, true);
            fileWriter.write(String.format("\n\n---------------------[%s] %s----------------------\n\n", article.getArticleID(), article.getTitle()));
            fileWriter.write(article.getContent());
            fileWriter.close();
        }
    }
}

