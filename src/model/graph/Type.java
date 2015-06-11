package model.graph;


public enum Type {
    NORMAL("NORMAL"),
    ESCARPE("ESCARPE"),
    PLAT("PLAT"),
    INONDE("INONDE");

    private String text;

    Type(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Type fromString(String text) {
        if (text != null) {
            for (Type t : Type.values()) {
                if (text.equalsIgnoreCase(t.text)) {
                    return t;
                }
            }
        }
        return null;
    }

}
