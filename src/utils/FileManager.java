package utils;

import run.config;
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
import javax.xml.transform.stream.StreamSource;
import java.io.File;


public class FileManager {

    /**
     * Charge un fichier xml dans le modèle
     * @param file xml
     * @return un graphe
     */
    public static Graph loadFileManager(File file) {

        Graph graph;
        NodeList nListEdge;
        NodeList nListNode;
        File fXmlFile;
        TypeEdge typeEdge;
        TypeNode typeNode;
        int id;
        double x, y;
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
                    if(typeNode.equals(TypeNode.INCENDIE))
                        node = new model.graph.Node(id, x, y, model.graph.Node.FIRE_DEFAULT_TEMPERATURE);
                    else
                        node = new model.graph.Node(id, x, y);
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
                    graph.addEdge(new Edge(nd1, nd2,typeEdge));
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

    /**
     * Sauvegarde un fichier xml à partir du modèle
     * @param file xml
     * @param g un graphe
     */
    public static void saveFileManager(File file, Graph g) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            doc.createTextNode(System.getProperty("line.separator"));
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
                if (n.getIntensity() > 0) {
                    type.setValue(TypeNode.INCENDIE.getText());
                } else {
                    type.setValue(TypeNode.NORMAL.getText());
                }

                staff.setAttributeNode(type);

            }

            for (Edge e : g.getAllEdges()) {
                // staff elements
                Element staff = doc.createElement("edge");
                rootElement.appendChild(staff);

                // set attribute to staff element
                Attr nd1 = doc.createAttribute("nd1");
                nd1.setValue(String.valueOf(e.getSource().getID()));
                staff.setAttributeNode(nd1);

                Attr nd2 = doc.createAttribute("nd2");
                nd2.setValue(String.valueOf(e.getDestination().getID()));
                staff.setAttributeNode(nd2);


                Attr type = doc.createAttribute("type");
                type.setValue(String.valueOf(e.getType()));
                staff.setAttributeNode(type);
            }

            // write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().
                    newTransformer( new StreamSource(config.pathStyleXslt));

            StreamResult result = new StreamResult(file);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

        } catch (Exception pce) {
            pce.printStackTrace();
        }

    }
}
