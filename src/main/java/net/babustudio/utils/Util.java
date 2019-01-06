package net.babustudio.utils;

public final class Util {

    public static String replace(final String content) {
        return content.replaceAll("</m_p><m_p>", "")
                .replaceAll("</m_p>", "")
                .replaceAll("<m_p>", "")
                .replaceAll("}", "")
                .replaceAll("\\s*\\[[^\\]]*\\]\\s*", "")
                .replaceAll("\\s*\\{[^\\}]*\\}\\s*", "")
                .replaceAll("\\s*\\([^\\)]*\\)\\s*", "")
                .replaceAll("\\{","").replaceAll("\\}","");
    }

    public static String nameReplace(final String content) {
        return content.replaceAll("\\s", "%20")
                .replaceAll("\\?", "%3F");
    }

    public static String titleReplace(final String content){
        return content.replaceAll("\\.[A-Za-z]*","");
    }

}
