package model.graph;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Logan Paul on 11/06/2015.
 */
public class GraphTest {

    Graph g;

    @Test
    public void testAddNode() throws Exception {
        g = new Graph();
        Node n1 = new Node(5,2.0,6.3,Type.NORMAL);
        Node n2 = new Node(0,0,0,Type.NORMAL);
        Node n3 = new Node(5,2.0,6.3,Type.NORMAL);
        Node n4 = new Node(-2,2.0,6.3,Type.NORMAL);
        Node n5 = new Node(1,9,15.2,Type.NORMAL);
        Node n6 = new Node(2,7.6,7.6,Type.INCENDIE);
        Node n7 = new Node(2,1,1,Type.INCENDIE);
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
            System.out.println(n.toString());
            System.out.println(g.findNode(n.getID()).toString());
            Assert.assertTrue(g.findNode(n.getID()).equals(n));
            System.out.println("_____");
        }
    }

    @Test
    public void testFindNode() throws Exception {

    }

    @Test
    public void testGetAllNodes() throws Exception {

    }

    @Test
    public void testGetAllFireNodes() throws Exception {

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