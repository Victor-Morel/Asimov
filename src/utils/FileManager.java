package utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;


public class FileManager {

    public FileManager(File file) {

        NodeList nListEdge;
        NodeList nListNode;
        File fXmlFile;


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

                    //create node
                    System.out.println("id : " + eElement.getAttribute("id"));
                    System.out.println("x : " + eElement.getAttribute("x"));
                    System.out.println("y : " + eElement.getAttribute("y"));
                    System.out.println("type : " + eElement.getAttribute("type"));

                }
            }

            nListEdge = doc.getElementsByTagName("edge");

            for (int temp = 0; temp < nListEdge.getLength(); temp++) {

                Node nNode = nListEdge.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    //create edge
                    System.out.println("nd1 : " + eElement.getAttribute("nd1"));
                    System.out.println("nd2 : " + eElement.getAttribute("nd2"));
                    System.out.println("type : " + eElement.getAttribute("type"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
