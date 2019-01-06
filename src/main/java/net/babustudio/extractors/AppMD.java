package net.babustudio.extractors;

import net.babustudio.models.Article;
import net.babustudio.models.MarkdownModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppMD extends App {

    private String markdownDirectory;

// --Commented out by Inspection START (2019/1/6, 10:15):
//    public AppMD() {
//        super();
//    }
// --Commented out by Inspection STOP (2019/1/6, 10:15)

    @Override
    public void getProperties() throws IOException {
        InputStream settings = new Thread().getContextClassLoader().getResourceAsStream("settings.properties");
        Properties properties = new Properties();
        properties.load(settings);

        this.connectionProperties = properties.getProperty("databaseUrl");
        this.markdownDirectory = properties.getProperty("markdownDirectory");
        this.outputDirectory = properties.getProperty("outputDirectory");
        System.out.println("properties' got.");
    }

    @Override
    public void output() throws IOException {
        System.out.println(markdownDirectory);
        String passagesURL = this.markdownDirectory + "/passages";
        String listeningsURL = this.markdownDirectory + "/listenings";
        //define the urls of different directories.

        File passages = new File(passagesURL);
        File listenings = new File(listeningsURL);
        passages.mkdirs();
        listenings.mkdirs();
        //create the directories

        System.out.println("I am here!");

        for (Article oneArticle : this.articles) {
            File outfile = new File(((oneArticle.getType() == 1) ? passagesURL : listeningsURL) + "/" + oneArticle.getTitle() + ".md");
            outfile.createNewFile();
            FileWriter fw = new FileWriter(outfile);
            fw.write(new MarkdownModel(oneArticle).toString());
            fw.close();
        }
    }
}
