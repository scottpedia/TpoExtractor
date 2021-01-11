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

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class Article implements Serializable {

    public int type;

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

    public int getType() {
        return type;
    }

// --Commented out by Inspection START (2019/1/6, 10:15):
//    public String toJson() {
//        return new GsonBuilder().create().toJson(this);
//    }
// --Commented out by Inspection STOP (2019/1/6, 10:15)

    public String toJson(boolean prettyOut) {
        if (prettyOut) return new GsonBuilder().setPrettyPrinting().create().toJson(this);
        else return new GsonBuilder().create().toJson(this);
    }
}
