package model.graph;


public enum TypeEdge {
    ESCARPE("ESCARPE"),
    PLAT("PLAT"),
    INONDE("INONDE");

    private String text;

    TypeEdge(String text) {
        this.text = text;
    }

    public static TypeEdge fromString(String text) {
        if (text != null) {
            for (TypeEdge t : TypeEdge.values()) {
                if (text.equalsIgnoreCase(t.text)) {
                    return t;
                }
            }
        }
        return null;
    }
}
