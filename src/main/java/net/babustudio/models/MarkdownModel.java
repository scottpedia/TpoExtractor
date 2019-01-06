package net.babustudio.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MarkdownModel extends Article {

    private static final String projectURL;

    static {
        InputStream settings = new Thread().getContextClassLoader().getResourceAsStream("settings.properties");
        Properties properties = new Properties();

        try {
            properties.load(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        projectURL = properties.getProperty("projectURL");
    }

    // --Commented out by Inspection (2019/1/6, 10:15):private String pathSuffix = null;

    public MarkdownModel(Article from) {
        super();
        this.title = from.title;
        this.articleID = from.articleID;
        this.content = from.content;
        this.type = from.type;
    }

// --Commented out by Inspection START (2019/1/6, 10:15):
//    public String getPathSuffix() {
//        return (this.type == 1) ? "/passages" : "/listenings";
//    }
// --Commented out by Inspection STOP (2019/1/6, 10:15)

    private String getMarkdownText() {
        String markdownText = String.format("# %s\n[Back to Index](%s)\n\n%s", this.title, projectURL + "/README.md", this.content);
        return markdownText;
    }

    @Override
    public String toString() {
        return this.getMarkdownText();
    }
}
