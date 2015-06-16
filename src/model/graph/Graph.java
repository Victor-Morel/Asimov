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
     * liste des aretes du graphe
     */
    private Set<Edge> edges;

    /**
     * Constructeur par defaut d'un graphe
     */
    public Graph() {
        this.setNodes(new HashSet<Node>());
        this.setEdges(new HashSet<Edge>());
    }

    /**
     * Methode d'ajout de noeud
     *
     * @param _node le noeud a ajouter au graphe
     */
    public void addNode(Node _node) {
        this.nodes.add(_node);
    }

    /**
     * Methode pour trouver un noeud dans le graphe a partir de son id
     *
     * @param id id du noeud recherche
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
     * Methode pour selectionner tous les noeuds du graphe
     *
     * @return tous les noeuds du graphe
     */
    public Set<Node> getAllNodes() {
        return this.nodes;
    }

    /**
     * Methode pour selectionner uniquement les noeuds en feu du graphe
     *
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
     * Methode qui compte le nombre de noeuds dans le graphe
     *
     * @return le nombre de noeuds du graphe
     */
    public int getNbNodes() {
        return this.nodes.size();
    }

    /**
     * Methode qui genere la liste des noeuds lies au noeud passe en parametre
     *
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
     * Methode indiquant s'il y a un arete entre 2 noeuds donnes
     *
     * @param _n1 premiere extremite de l'arete
     * @param _n2 deuxieme extremite de l'arete
     * @return true s'il y a un arete entre _n1 et _n2, false sinon
     */
    public boolean hasEdge(Node _n1, Node _n2) {
        for (Edge e : this.getAllEdges()) {
            if ((e.getSource().equals(_n1) && e.getDestination().equals(_n2)) ||
                    (e.getSource().equals(_n2) && e.getDestination().equals(_n1))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Methode d'ajout d'arete dans le graphe
     *
     * @param _edge arete a ajouter au graphe
     */
    public void addEdge(Edge _edge) {
        this.getAllEdges().add(_edge);
    }

    /**
     * Methode qui retourne la liste des aretes du graphe issus du noeud passe en parametre
     *
     * @param _n noeud dont on veut connaitre les aretes lies
     * @return les aretes lies au noeud passe en parametre
     */
    public List<Edge> getEdges(Node _n) {
        List<Edge> requiredEdges = new ArrayList<Edge>();
        for (Edge e : this.getAllEdges()) {
            if (e.getSource().equals(_n) || e.getDestination().equals(_n)) {
                requiredEdges.add(e);
            }
        }
        return requiredEdges;
    }

    /**
     * Methode qui renvoie l'arete entre 2 noeuds passes en parametre
     *
     * @param _n1 premiere extremite de l'arete
     * @param _n2 deuxieme extremite de l'arete
     * @return l'arete entre _n1 et _n2 s'il existe, null sinon
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
     * Retire du graphe l'arete passee en parametre
     *
     * @param e arrete a retirer du graphe
     */
    public void removeEdge(Edge e) {
        if (this.getAllEdges().contains(e)) {
            this.getAllEdges().remove(e);
        }
    }

    /**
     * @return un String correspondant au graphe
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
     *
     * @param nodes liste des noeuds a setter
     */
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Methode qui selectionne la liste complete des aretes du graphe
     *
     * @return la liste des aretes du graphe
     */
    public Set<Edge> getAllEdges() {
        return edges;
    }

    /**
     * Permet de setter la liste des aretes du graphe
     *
     * @param edges liste des aretes a setter
     */
    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }


    /**
     * Fonction permettant d'embrasser les noeuds non-enflammes
     */
    public void igniteNodes() {
        Set<Node> allNodes = this.getAllNodes();
        for(Node n : allNodes) {
            double rand = Math.random();
            if(rand < Node.IGNITE_PROBABILITY && 0 == n.getIntensity()) {
                n.setIntensity(Node.FIRE_DEFAULT_TEMPERATURE);
                System.out.println("New fire in " + n.toString());
            }
        }
    }

    /**
     * Methode qui genere le sous-graphe d'un robot. Le sous-graphe est identique au graphe de base, mais on y a retire les aretes que le robot ne pouvais pas emprunter, en fonction du type de robot
     *
     * @param robotType le type de robot utilise
     * @return le sous-graphe dans lequel le robot peut se deplacer
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
     * Methode qui indique si l'arete passe en parametre est accessible au type de robot donne
     *
     * @param e         arete a tester
     * @param robotType type de robot voulant emprunter l'arete
     * @return true si le robot donne peut emprunter l'arete donnee, false sinon
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
     * @param o le graphe a comparer a this
     * @return true si les graphes sont identiques, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Graph graph = (Graph) o;

        return !(nodes != null ? !nodes.equals(graph.nodes) : graph.nodes != null) && !(edges != null ? !edges.equals(graph.edges) : graph.edges != null);
    }

    @Override
    public int hashCode() {
        int result = nodes != null ? nodes.hashCode() : 0;
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }

    /**
     * Destructeur de graphe
     *
     * @throws Throwable
     */
    @Override
    public void finalize() throws Throwable {
        for (Node n : this.getAllNodes()) {
            n.reset();
        }
        for (Edge e : this.getAllEdges()) {
            e.reset();
        }
    }


}
