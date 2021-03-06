package model.robot;

/**
 * Type enum pour le type de recherche du robot
 */
public enum ResearchType {
    DIJKSTRA("DIJKSTRA"),
    ASTAR("ASTAR");

    private String text;

    ResearchType(String text) {
        this.text = text;
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
