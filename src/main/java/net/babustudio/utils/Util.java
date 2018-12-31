package net.babustudio.utils;

public final class Util {

    public static final String replace(final String content) {
        return content.replaceAll("</m_p><m_p>", "")
                .replaceAll("</m_p>", "")
                .replaceAll("<m_p>", "")
                .replaceAll("}", "")
                .replaceAll("\\s*\\[[^\\]]*\\]\\s*", "")
                .replaceAll("\\s*\\{[^\\}]*\\}\\s*", "")
                .replaceAll("\\s*\\([^\\)]*\\)\\s*", "");
    }

    public static final String nameReplace(final String content) {
        return content.replaceAll("\\s", "%20")
                .replaceAll("\\?", "%3F");
    }

    public static final String titleReplace(final String content){
        return content.replaceAll("\\.[A-Za-z]","");
    }

}
