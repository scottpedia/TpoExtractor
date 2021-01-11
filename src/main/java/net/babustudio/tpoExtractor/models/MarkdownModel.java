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

package net.babustudio.tpoExtractor.models;

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
