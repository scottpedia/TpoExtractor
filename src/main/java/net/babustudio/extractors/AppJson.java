package net.babustudio.extractors;

import net.babustudio.models.Article;

public class AppJson extends App {
    @Override
    public void output() {
        for (Article i : this.articles) {
            if (i.type == 1) System.out.println(i.toJson(true));
        }
    }

}
