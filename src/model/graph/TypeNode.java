package model.graph;


public enum TypeNode {
    NORMAL("NORMAL"),
    INCENDIE("INCENDIE");

    private String text;

    TypeNode(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TypeNode fromString(String text) {
        if (text != null) {
            for (TypeNode t : TypeNode.values()) {
                if (text.equalsIgnoreCase(t.text)) {
                    return t;
                }
            }
        }
        return null;
    }
}
