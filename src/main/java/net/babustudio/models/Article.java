package net.babustudio.models;

import com.google.gson.GsonBuilder;
import net.babustudio.Type;

import java.io.Serializable;

public class Article implements Serializable {

    public Type type;

    public String articleID, title, content;

    public Article() {
    }

    public String getArticleID() {
        return articleID;
    }

    @Override
    public String toString() {
        return String.format("ArticleID: %s\nTitle: %s\n%s", articleID, title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Type getType(){ return type; }

    public String toJson() {
        return new GsonBuilder().create().toJson(this);
    }

    public String toJson(boolean prettyOut) {
        if (prettyOut) return new GsonBuilder().setPrettyPrinting().create().toJson(this);
        else return new GsonBuilder().create().toJson(this);
    }
}
