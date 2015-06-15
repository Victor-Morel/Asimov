package model.robot;

public enum ResearchType {
    DIJKSTRA("DIJKSTRA"),
    ASTAR("ASTAR");

    private String text;

    ResearchType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ResearchType fromString(String text) {
        if (text != null) {
            for (ResearchType t : ResearchType.values()) {
                if (text.equalsIgnoreCase(t.text)) {
                    return t;
                }
            }
        }
        return null;
    }
}
