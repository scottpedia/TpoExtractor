/*
Copyright (C) 2018-2021 Scott X. Liang <me@theanonymity.de>

This file is part of TpoExtractor.

TpoExtractor is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

TpoExtractor is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with TpoExtractor.  If not, see <https://www.gnu.org/licenses/>.
*/

package net.babustudio.tpoExtractor.extractors;

import net.babustudio.tpoExtractor.models.Article;
import net.babustudio.tpoExtractor.models.MarkdownModel;

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
