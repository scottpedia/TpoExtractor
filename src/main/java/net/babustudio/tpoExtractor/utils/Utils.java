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
