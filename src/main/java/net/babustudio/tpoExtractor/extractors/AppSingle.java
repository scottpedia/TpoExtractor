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

