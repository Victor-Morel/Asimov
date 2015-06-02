package utils;

import model.graph.Edge;
import model.graph.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class FileManager {

    public FileManager(File file) {

        NodeList nListEdge;
        NodeList nListNode;
        File fXmlFile;
        int id, nd1, nd2;
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

                    // TODO add node to graph

                }
            }

            nListEdge = doc.getElementsByTagName("edge");

            for (int temp = 0; temp < nListEdge.getLength(); temp++) {

                Node nNode = nListEdge.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    nd1 = Integer.parseInt(eElement.getAttribute("nd1"));
                    nd2 = Integer.parseInt(eElement.getAttribute("nd2"));
                    type = Type.fromString(eElement.getAttribute("type"));
                    Edge e = new Edge(nd1, nd2, type);

                    // TODO add edge to graph
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
