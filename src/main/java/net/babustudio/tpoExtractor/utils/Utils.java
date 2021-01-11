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


package net.babustudio.tpoExtractor.utils;

import java.io.File;
import java.io.FileFilter;

public final class Utils {

    public static String replace(final String content) {
        return content.replaceAll("</m_p><m_p>", "")
                .replaceAll("</m_p>", "")
                .replaceAll("<m_p>", "")
                .replaceAll("}", "")
                .replaceAll("\\s*\\[[^\\]]*\\]\\s*", "")
                .replaceAll("\\s*\\{[^\\}]*\\}\\s*", "")
                .replaceAll("\\s*\\([^\\)]*\\)\\s*", "")
                .replaceAll("\\{", "").replaceAll("\\}", "");
    }

    public static String nameReplace(final String content) {
        return content.replaceAll("\\s", "%20")
                .replaceAll("\\?", "%3F");
    }

    public static String titleReplace(final String content) {
        return content.replaceAll("\\.[A-Za-z]*", "");
    }

    public static void cleanup(String directory) {
        System.out.println("Cleaning directory: " + directory);
        File dir = new File(directory);
        if (dir.isDirectory()) {
            File[] filesInIt;
            filesInIt = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile();
                }
            });
            for (File file : filesInIt) {
                file.delete();
            }
        }
    }

    public static void cleanup(String directory, FileFilter fileFilter) {
        File dir = new File(directory);
        if (dir.isDirectory()) {
            File[] filesToDelete = dir.listFiles(fileFilter);
            for (File file : filesToDelete) {
                file.delete();
            }
        }
    }

}
