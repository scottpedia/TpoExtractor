package net.babustudio.extractors;

import net.babustudio.models.Article;

import java.io.IOException;

public class AppJson extends App{
    @Override
    public void output()  {
        for(Article i : this.articles){
            System.out.println(i.toJson(true));
        }
    }

}
