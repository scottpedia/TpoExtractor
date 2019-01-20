package net.babustudio.tpoExtractor.extractors;

import net.babustudio.tpoExtractor.models.Article;

public class AppJson extends App {
    @Override
    public void output() {
        for (Article i : this.articles) {
            if (i.type == 1) System.out.println(i.toJson(true));
        }
    }

}

