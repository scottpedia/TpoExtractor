package net.babustudio;

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

}
