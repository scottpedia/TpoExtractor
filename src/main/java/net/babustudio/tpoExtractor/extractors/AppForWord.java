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
import net.babustudio.tpoExtractor.models.DocxModel;

import java.io.File;
import java.io.IOException;

public class AppForWord extends App {

    @Override
    public void output() throws IOException {// to be continued...
        int counter = 1;
        for (Article article : this.articles) {
            if (!article.getTitle().isEmpty())
                if (article.getType() == 1)
                    new DocxModel(article).exportToWord(new File(this.outputDirectory + "/" + "[" + counter++
                            + "] " + article.getTitle() + ".docx"));
        }
    }
}
