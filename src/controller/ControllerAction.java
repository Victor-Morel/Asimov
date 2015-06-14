package controller;

import model.graph.Edge;
import model.graph.Node;
import model.graph.TypeEdge;
import model.robot.AllTerrainRobot;
import model.robot.CaterpillarRobot;
import model.robot.LeggedRobot;
import model.robot.TypeRecherche;
import utils.FileManager;
import view.Dialog.ChooseFile;
import view.Dialog.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ControllerAction implements ActionListener {

    /**
     * Chemin pour acceder au fichier data
     */
    String path;
    /**
     * Boolean pour savoir quelle bouton à été selectionner
     */
    Boolean node, fire, plat, inonde, escarpe,
            terrain, pate, chenille;

    /**
     * Controlleur
     */
    Controller control;

    /**
     * Consctruit un controlleur des actions
     * @param _path
     * @param control
     */
    public ControllerAction(String _path, Controller control) {
        super();
        this.path = _path;
        this.control = control;
        initialization();
    }

    private void initialization() {
        node = false;
        fire = false;
        plat = false;
        inonde = false;
        escarpe = false;
        terrain = false;
        pate = false;
        chenille = false;
    }

    /**
     * Chaque action de chaque bouton
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().matches("Noeud")) {
            this.initialization();
            node = true;
        } else if (e.getActionCommand().matches("Feu")) {
            this.initialization();
            fire = true;
        } else if (e.getActionCommand().matches("Plat")) {
            this.initialization();
            plat = true;
        } else if (e.getActionCommand().matches("Escarpe")) {
            this.initialization();
            escarpe = true;
        } else if (e.getActionCommand().matches("Inonde")) {
            this.initialization();
            inonde = true;
        } else if (e.getActionCommand().matches("Tout Terrain")) {
            this.initialization();
            terrain = true;
        } else if (e.getActionCommand().matches("Chenille")) {
            this.initialization();
            chenille = true;
        } else if (e.getActionCommand().matches("Pates")) {
            this.initialization();
            pate = true;
        } else if (e.getActionCommand().matches("Save")) {
            saveFile();
        } else if (e.getActionCommand().matches("Load XML")) {
            if (0 == control.getGraph().getNbNodes()) {
                loadFileXML();
            } else {
                JDialog jdialog = new JDialog();
                switch (jdialog.getElementChoose()) {
                    case 0:
                        saveFile();
                        control.reset();
                        loadFileXML();
                        break;
                    case 1:
                        control.reset();
                        loadFileXML();
                        break;
                    default:
                        break;
                }

            }
        } else if (e.getActionCommand().matches("Load IMAGE")) {

        }

    }

    /**
     * Verifie si dans il existe un noeud au coordonnée x et y
     * @param x coordonnée x de la souris
     * @param y coordonnée y
     * @return Node selectioner ou null
     */
    protected Node clickOnNode(int x, int y) {
        for (Node n : control.getGraph().getAllNodes()) {
            for (int i = -10; i < 10; i++) {
                for (int j = -10; j < 10; j++) {
                    if ((x == (int) n.getX() + i) && (y == (int) n.getY() + j)) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Affiche si nouveau noeud courant selectionner
     * @param x parametre x de la souris
     * @param y parametre y de la souris
     * @return nouveau noeud courant ou null
     */
    protected Node checkCurrentNode(int x, int y) {
        Node currentNode;
        currentNode = clickOnNode(x, y);
        if (null != currentNode) {
            currentNode.setCurrentNode(true);
            checkOnlyNode(currentNode.getID());
        }
        return currentNode;
    }

    /**
     * Ajouter noeud
     *
     * @param x cordonnée x du noeud
     * @param y cordonnée y du noeud
     */
    public void addNode(int x, int y) {

        if (node) {
            control.addNode(new Node(x, y));
        } else if (fire) {
            control.addNode(new Node(x, y, Node.FIRE_DEFAULT_TEMPERATURE));
        }
    }

    /**
     * Ajouter un arc
     *
     * @param node1 noeud 1 du nouveau arc
     * @param node2 noeud 2 du nouveau arc
     */
    public void addEdge(Node node1, Node node2) {
        double valuation = 0;
        if (plat) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.PLAT));
        } else if (escarpe) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.ESCARPE));
        } else if (inonde) {
            control.addEdge(new Edge(node1, node2, valuation, TypeEdge.INONDE));
        }
    }

    /**
     * Ajouter un robot
     *
     * @param currentNode noeud ou le robot sera instancié
     */
    public void addRobot(Node currentNode) {
        int _capacity = 10;
        TypeRecherche type = TypeRecherche.ASTAR;
        if (terrain) {
            control.addRobot(new AllTerrainRobot(type, _capacity));
        } else if (chenille) {
            control.addRobot(new CaterpillarRobot(type, _capacity));
        } else if (pate) {
            control.addRobot(new LeggedRobot(type, _capacity));
        }
    }


    /**
     * Verifie qu'il y a un seul noeud courant
     *
     * @param currentNode
     */
    private void checkOnlyNode(int currentNode) {
        for (Node n : control.getGraph().getAllNodes()) {
            if (n.getID() != currentNode && n.isCurrentNode()) {
                n.setCurrentNode(false);
            }
        }
    }

    /**
     * Save File
     */
    private void saveFile() {
        ChooseFile chooseFile = new ChooseFile("Sauvegarder", this.path);
        File file = chooseFile.selectFile();
        if (null != file) {
            FileManager.saveFileManager(file, control.getGraph());
        }
    }

    /**
     * Load File XML
     */
    private void loadFileXML() {
        ChooseFile chooseFile = new ChooseFile("Charger", this.path);
        File file = chooseFile.selectFile();
        if (null != file) {
            control.setGraph(FileManager.loadFileManager(file));
            control.displayGraphe();
        }
    }
}


