package utils;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.TypeEdge;
import model.graph.TypeNode;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class FileManager {

    public static Graph loadFileManager(File file) {

        Graph graph;
        NodeList nListEdge;
        NodeList nListNode;
        File fXmlFile;
        TypeEdge typeEdge;
        TypeNode typeNode;
        int id;
        double x, y, distance;
        model.graph.Node nd1, nd2;
        model.graph.Node node;


        graph = new Graph();

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
                    typeNode = TypeNode.fromString(eElement.getAttribute("type"));
                    node = new model.graph.Node(id, x, y, typeNode);
                    graph.addNode(node);
                }
            }


            nListEdge = doc.getElementsByTagName("edge");
            for (int temp = 0; temp < nListEdge.getLength(); temp++) {
                Node nNode = nListEdge.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    nd1 = graph.findNode(Integer.parseInt(eElement.getAttribute("nd1")));
                    nd2 = graph.findNode(Integer.parseInt(eElement.getAttribute("nd2")));
                    typeEdge = TypeEdge.fromString(eElement.getAttribute("type"));
                    distance = EuclidianDistance.getDistance(nd1, nd2);
                    graph.addEdge(new Edge(nd1, nd2, distance, typeEdge));
                }
            }
        } catch (
                Exception e
                )

        {
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

            for (model.graph.Node n : g.getAllNodes()) {
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

            for (Edge e : g.getAllEdges()) {
                // staff elements
                Element staff = doc.createElement("robot");
                rootElement.appendChild(staff);

                // set attribute to staff element
                Attr nd1 = doc.createAttribute("nd1");
                nd1.setValue(String.valueOf(e.getSource().getID()));
                System.out.println(String.valueOf(e.getSource().getID()));
                staff.setAttributeNode(nd1);

                Attr nd2 = doc.createAttribute("nd2");
                nd2.setValue(String.valueOf(e.getDestination().getID()));
                staff.setAttributeNode(nd2);
                System.out.println(String.valueOf(e.getDestination().getID()));

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


        } catch (Exception pce) {
            pce.printStackTrace();
        }

    }
}
