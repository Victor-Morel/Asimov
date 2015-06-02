package utils;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;


public class FileManager {

    public static Graph loadFileManager(File file) {

        Graph graph = new Graph();
        NodeList nListEdge;
        NodeList nListNode;
        File fXmlFile;
        int id;
        model.graph.Node nd1, nd2;
        double x, y;
        Type type;

        try {

            fXmlFile = file;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            nListNode = doc.getElementsByTagName("node");

            for (int temp = 0; temp < nListNode.getLength(); temp++) {

                Node nNode = nListNode.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    id = Integer.parseInt(eElement.getAttribute("id"));
                    x = Double.parseDouble(eElement.getAttribute("x"));
                    y = Double.parseDouble(eElement.getAttribute("y"));
                    type = Type.fromString(eElement.getAttribute("type"));
                    model.graph.Node n = new model.graph.Node(id, x, y, type);
                    graph.addNode(n);
                }
            }

            nListEdge = doc.getElementsByTagName("edge");
            for (int temp = 0; temp < nListEdge.getLength(); temp++) {
                Node nNode = nListEdge.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    nd1 = graph.findNode(Integer.parseInt(eElement.getAttribute("nd1")));
                    nd2 = graph.findNode(Integer.parseInt(eElement.getAttribute("nd2")));
                    type = Type.fromString(eElement.getAttribute("type"));
                    p1 = new Point(nd1.getX(), nd1.getY());
                    p2 = new Point(nd2.getX(), nd2.getY());
                    double distance = EuclidianDistance.getDistance(nd1, nd2);
                    graph.addEdge(new Edge(nd1, nd2, distance, type));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}
