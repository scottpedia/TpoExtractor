package net.babustudio.tpoExtractor;

import net.babustudio.models.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppSingle extends App {
    public AppSingle() {
        super();
    }

    @Override
    public void output() throws IOException {
        File dir = new File(this.outputDirectory);
        if (!dir.exists() || !dir.isDirectory()) {
            IOException ioException = new IOException("Failed to access the directory.");
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
