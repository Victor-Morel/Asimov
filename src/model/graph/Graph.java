package model.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Graph {

    /**
     * liste des noeuds du graphe
     */
    private Set<Node> nodes;

    /**
     * liste des arètes du graphe
     */
    private Set<Edge> edges;

    /**
     * Constructeur par défault d'un graphe
     */
    public Graph() {
        this.setNodes(new HashSet<Node>());
        this.setEdges(new HashSet<Edge>());
    }

    /**
     * Méthode d'ajout de noeud
     * @param _node le noeud à ajouter au graphe
     */
    public void addNode(Node _node) {
        this.nodes.add(_node);
    }

    /**
     * Méthode pour trouver un noeud dans le graphe à partir de son id
     * @param id id du noeud recherché
     * @return le noeud avec l'id correspondant, null s'il n'y en a pas
     */
    public Node findNode(int id) {
        for (Node n : nodes) {
            if (id == n.getID()) {
                return n;
            }
        }
        return null;
    }

    /**
     * Méthode pour sélectionner tous les noeuds du graphe
     * @return tous les noeuds du graphe
     */
    public Set<Node> getAllNodes() {
        return this.nodes;
    }

    /**
     * Méthode pour sélectionner uniquement les noeuds en feu du graphe
     * @return tous les noeuds en feu du graphe
     */
    public Set<Node> getAllFireNodes() {
        Set<Node> fireNodes = new HashSet<Node>();
        for (Node n : this.nodes) {
            if (n.getIntensity() > 0) {
                fireNodes.add(n);
            }
        }
        return fireNodes;
    }

    /**
     * Méthode qui compte le nombre de noeuds dans le graphe
     * @return le nombre de noeuds du graphe
     */
    public int getNbNodes() {
        return this.nodes.size();
    }

    /**
     * Méthode qui génère la liste des noeuds liés au noeud passé en paramètre
     * @param _n le noeud dont on souhaite connaitre les voisins
     * @return la liste des voisins
     */
    public List<Node> getNextNodes(Node _n) {
        List<Node> destNodes = new ArrayList<Node>();
        for (Edge e : this.getEdges(_n)) {
            if (e.getDestination().equals(_n)) {
                destNodes.add(e.getSource());
            } else {
                destNodes.add(e.getDestination());
            }
        }
        return destNodes;
    }

    /**
     * Méthode indiquant s'il y a un arète entre 2 noeuds donnés
     * @param _n1 première extrémité de l'arète
     * @param _n2 deuxième extrémité de l'arète
     * @return true s'il y a un arète entre _n1 et _n2, false sinon
     */
    public boolean hasEdge(Node _n1, Node _n2) {
        for (Edge e : this.getAllEdges()) {
            if ((e.getSource().equals(_n1) && e.getDestination().equals(_n2)) ||
                    (e.getSource().equals(_n2) && e.getDestination().equals(_n1)) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode d'ajout d'arète dans le graphe
     * @param _edge arète à ajouter au graphe
     */
    public void addEdge(Edge _edge) {
        this.getAllEdges().add(_edge);
    }

    /**
     * Méthode qui retourne la liste des arètes du graphe issus du noeud passé en paramètre
     * @param _n noeud dont on veut connaitre les arètes liés
     * @return les arètes liés au noeud passé en paramètre
     */
    public List<Edge> getEdges(Node _n) {
        List<Edge> requiredEdges = new ArrayList<Edge>();
        for (Edge e : this.getAllEdges()) {
            if (e.getSource().equals(_n) || e.getDestination().equals(_n)) {
                //System.out.println(e.getSource().toString()+"   ____________    "+e.getDestination().toString());
                requiredEdges.add(e);
            }
        }
        return requiredEdges;
    }

    /**
     * Methode qui renvoie l'arète entre 2 noeuds passés en paramètre
     * @param _n1 première extrémité de l'arète
     * @param _n2 deuxième extrémité de l'arète
     * @return l'arète entre _n1 et _n2 s'il existe, null sinon
     */
    public Edge getEdge(Node _n1, Node _n2) {
        if (hasEdge(_n1, _n2)) {
            for (Edge e : this.getAllEdges()) {
                if (e.getSource().equals(_n1) && e.getDestination().equals(_n2) ||
                        e.getSource().equals(_n2) && e.getDestination().equals(_n1)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * @return un String corresponfant au graphe
     */
    public String toString() {
        String s = "";
        for (Node n : this.getAllNodes()) {
            s = s + "[" + n.toString() + " : ";
            s = s + this.getEdges(n).toString();
            s = s + " ]\n";
        }
        return s;
    }

    /**
     * Permet de setter la liste des noeuds du graphe
     * @param nodes liste des noeuds à setter
     */
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Méthode qui sélectionne la liste complète des arètes du graphe
     * @return la liste des arètes du graphe
     */
    public Set<Edge> getAllEdges() {
        return edges;
    }

    /**
     * Permet de setter la liste des arètes du graphe
     * @param edges liste des arètes à setter
     */
    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }

    /**
     * Méthode qui génère le sous-graphe d'un robot. Le sous-graphe est identique au graphe de base, mais on y a retiré les arètes que le robot ne pouvais pas emprunter, en fonction du type de robot
     * @param robotType le type de robot utilisé
     * @return le sous-graphe dans lequel le robot peut se déplacer
     */
    public Graph getSubGraph(int robotType) {
        /**
         * type 1 : allterrain
         * type 2 : caterpillar
         * type 3 : legged
         */
        Graph subgraph = new Graph();
        subgraph.setNodes(this.getAllNodes());
        for (Edge e : this.edges) {
            if (edgeAuthorized(e, robotType)) {
                subgraph.addEdge(e);
            }
        }
        return subgraph;
    }

    /**
     * Méthode qui indique si l'arète passé en paramètre est accessible au type de robot donné
     * @param e arète à tester
     * @param robotType type de robot voulant emprunter l'arète
     * @return true si le robot donné peut emprunter l'arète donnée, false sinon
     */
    public Boolean edgeAuthorized(Edge e, int robotType) {
        switch (robotType) {
            case 1:
                return true;
            case 2:
                if ((e.getType() == TypeEdge.PLAT) || (e.getType() == TypeEdge.INONDE)) {
                    return true;
                }
                break;
            case 3:
                if ((e.getType() == TypeEdge.PLAT) || (e.getType() == TypeEdge.ESCARPE)) {
                    return true;
                }
                break;

            default:
                break;
        }
        return false;
    }

    /**
     * @param o le graphe a comparer à this
     * @return true si les graphes sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        if (nodes != null ? !nodes.equals(graph.nodes) : graph.nodes != null) return false;
        return !(edges != null ? !edges.equals(graph.edges) : graph.edges != null);
    }

    @Override
    public int hashCode() {
        int result = nodes != null ? nodes.hashCode() : 0;
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }

    /**
     * Destructeur de graphe
     * @throws Throwable
     */
    @Override
    public void finalize() throws Throwable{
        for(Node n : this.getAllNodes()) {
            n.reset();
        }
        for(Edge e : this.getAllEdges()) {
            e.reset();
        }
    }


}
