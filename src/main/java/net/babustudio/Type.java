package net.babustudio;

public enum Type {
    ARTICLE(1), LECTURE(2), CONVERSATION(3);

    private int theTypeValue;

    Type(int theType) {
        theTypeValue = theType;
    }

    public int getTypeValue() {
        return theTypeValue;
    }
}