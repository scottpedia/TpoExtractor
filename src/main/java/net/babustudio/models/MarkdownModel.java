package net.babustudio.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MarkdownModel extends Article{

    private String markdownText = null;
    private String pathSuffix = null;
    private static String projectURL;

    static {
        InputStream settings = new Thread().getContextClassLoader().getResourceAsStream("settings.properties");
        Properties properties = new Properties();

        try{
            properties.load(settings);
        }catch (IOException e){
            e.printStackTrace();
        }

        projectURL = properties.getProperty("projectURL");
    }

    public MarkdownModel(Article from){
        super();
        this.title = from.title;
        this.articleID = from.articleID;
        this.content = from.content;
        this.type = from.type;
    }

    public String getPathSuffix(){
        return (this.type == 1)?"/passages":"/listenings";
    }

    public String getMarkdownText(){
        markdownText = String.format("# %s\n[Back to Index](%s)\n\n%s",this.title,this.projectURL+"/README.md",this.content);
        return this.markdownText;
    }

    @Override
    public String toString(){
        return this.getMarkdownText();
    }
}
