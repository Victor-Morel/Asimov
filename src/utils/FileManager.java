package utils;

import model.graph.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                    double distance = EuclidianDistance.getDistance(nd1, nd2);
                    graph.addEdge(new Edge(nd1, nd2, distance, type));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static void saveFileManager(File file, Graph g) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("osm");
            doc.appendChild(rootElement);

            for (model.graph.Node n : g.getAllNodes()){
                // staff elements
                Element staff = doc.createElement("node");
                rootElement.appendChild(staff);

                // set attribute to staff element
                Attr id = doc.createAttribute("id");
                id.setValue(String.valueOf(n.getID()));
                staff.setAttributeNode(id);

                Attr x = doc.createAttribute("x");
                x.setValue(String.valueOf(n.getX()));
                staff.setAttributeNode(x);

                Attr y = doc.createAttribute("y");
                y.setValue(String.valueOf(n.getY()));
                staff.setAttributeNode(y);

                Attr type = doc.createAttribute("type");
                type.setValue(String.valueOf(n.getType()));
                staff.setAttributeNode(type);

            }

            for (Edge e : g.getEdges()){
                // staff elements
                Element staff = doc.createElement("edge");
                rootElement.appendChild(staff);

                // set attribute to staff element
                Attr nd1 = doc.createAttribute("nd1");
                nd1.setValue(String.valueOf(e.getSource()));
                staff.setAttributeNode(nd1);

                Attr nd2 = doc.createAttribute("nd2");
                nd2.setValue(String.valueOf(e.getDestination()));
                staff.setAttributeNode(nd2);

                Attr type = doc.createAttribute("type");
                type.setValue(String.valueOf(e.getType()));
                staff.setAttributeNode(type);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (Exception pce) {
            pce.printStackTrace();
        }

    }
}
