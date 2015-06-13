package model.robot;

public enum TypeRecherche {
    DIJKSTRA("DIJKSTRA"),
    ASTAR("ASTAR");

    private String text;

    TypeRecherche(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static TypeRecherche fromString(String text) {
        if (text != null) {
            for (TypeRecherche t : TypeRecherche.values()) {
                if (text.equalsIgnoreCase(t.text)) {
                    return t;
                }
            }
        }
        return null;
    }
}
