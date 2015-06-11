package test.modelTest;

import junit.framework.Assert;
import model.graph.Graph;
import model.graph.Node;
import model.graph.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Logan Paul on 11/06/2015.
 */
public class GraphTest {

    @Test
    public void testAddNode() throws Exception {
        Graph g = new Graph();
        Node n1 = new Node(5,2.0,6.3);
        Node n2 = new Node(0,0,0);
        Node n3 = new Node(5,1.0,6.3,0);
        Node n4 = new Node(-2,2.0,6.3,120);
        Node n5 = new Node(1,9,15.2,0);
        Node n6 = new Node(2,7.6,7.6,1);
        Node n7 = new Node(2,1,1,Integer.MAX_VALUE);
        List<Node> l = new ArrayList<Node>();
        l.add(n1);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        l.add(n5);
        l.add(n6);
        l.add(n7);
        for(Node n : l) {
            g.addNode(n);
            Assert.assertTrue(g.findNode(n.getID()).equals(n));
        }
    }

    @Test
    public void testFindNode() throws Exception {
        Graph g = new Graph();
        Node n1 = new Node(5,2.0,6.3);
        Node n2 = new Node(0,0,0);
        Node n3 = new Node(5,1.0,6.3,0);
        Node n4 = new Node(-2,2.0,6.3,120);
        List<Node> l = new ArrayList<Node>();
        l.add(n1);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        for(Node n : l) {
            g.addNode(n);
        }
        Assert.assertTrue(g.findNode(0).equals(l.get(1)));
        Assert.assertTrue(g.findNode(5).equals(l.get(0)));
        Assert.assertTrue(g.findNode(-2).equals(l.get(3)));
    }

    @Test
    public void testGetAllNodes() throws Exception {
        Graph g = new Graph();
        Node n1 = new Node(5,2.0,6.3);
        Node n2 = new Node(0,0,0);
        Node n3 = new Node(5,1.0,6.3,0);
        Node n4 = new Node(-2,2.0,6.3,120);
        Node n5 = new Node(1,9,15.2,0);
        Node n6 = new Node(2,7.6,7.6,1);
        Node n7 = new Node(2,1,1,Integer.MAX_VALUE);
        List<Node> l = new ArrayList<Node>();
        l.add(n1);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        l.add(n5);
        l.add(n6);
        l.add(n7);
        for(Node n : l) {
            g.addNode(n);
        }
        Set l2 = g.getAllNodes();
        for(Node n : l) {
            Assert.assertTrue(l2.contains(n));
        }
    }

    @Test
    public void testGetAllFireNodes() throws Exception {
        Graph g = new Graph();
        Node n1 = new Node(5,2.0,6.3);
        Node n2 = new Node(0,0,0);
        Node n3 = new Node(5,1.0,6.3,0);
        Node n4 = new Node(-2,2.0,6.3,120);
        Node n5 = new Node(1,9,15.2,0);
        Node n6 = new Node(2,7.6,7.6,1);
        Node n7 = new Node(2,1,1,Integer.MAX_VALUE);
        List<Node> l = new ArrayList<Node>();
        l.add(n1);
        l.add(n2);
        l.add(n3);
        l.add(n4);
        l.add(n5);
        l.add(n6);
        l.add(n7);
        for(Node n : l) {
            g.addNode(n);
        }
        Set l2 = g.getAllFireNodes();
        for(Node n : l) {
            if(n.getIntensity() > 0) {
                Assert.assertTrue(l2.contains(n));
            }
        }
    }

    @Test
    public void testGetNbNodes() throws Exception {

    }

    @Test
    public void testGetNextNodes() throws Exception {

    }

    @Test
    public void testHasEdge() throws Exception {

    }

    @Test
    public void testAddEdge() throws Exception {

    }

    @Test
    public void testGetSubGraph() throws Exception {

    }

    @Test
    public void testEdgeAuthorized() throws Exception {

    }
}