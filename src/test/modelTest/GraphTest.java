package test.modelTest;

import junit.framework.Assert;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.Type;
import org.junit.Before;
import org.junit.Test;
import utils.EuclidianDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Logan Paul on 11/06/2015.
 */
public class GraphTest {

    Graph g;
    Node n1;
    Node n2;
    Node n3;
    Node n4;
    Node n5;
    Node n6;
    Node n7;
    Edge e1;
    Edge e2;
    Edge e3;
    Edge e4;
    List<Node> ln;
    List<Edge> le;

    @Before
    public void setUp() {
        g = new Graph();
        n1 = new Node(5,2.0,6.3);
        n2 = new Node(0,0,0);
        n3 = new Node(9,1.0,6.3,0);
        n4 = new Node(-2,2.0,6.3,120);
        n5 = new Node(1,9,15.2,0);
        n6 = new Node(2,7.6,7.6,1);
        n7 = new Node(2,1,1,Integer.MAX_VALUE);
        e1 = new Edge(n1,n2, EuclidianDistance.getDistance(n1,n2),Type.PLAT);
        e2 = new Edge(n1,n3, EuclidianDistance.getDistance(n1,n3),Type.ESCARPE);
        e3 = new Edge(n2,n3, EuclidianDistance.getDistance(n2,n3),Type.PLAT);
        e4 = new Edge(n5,n6, EuclidianDistance.getDistance(n5,n6),Type.INONDE);
        ln = new ArrayList<Node>();
        le = new ArrayList<Edge>();
        ln.add(n1);
        ln.add(n2);
        ln.add(n3);
        ln.add(n4);
        ln.add(n5);
        ln.add(n6);
        ln.add(n7);
        le.add(e1);
        le.add(e2);
        le.add(e3);
        le.add(e4);
    }

    @Test
    public void testAddNode() throws Exception {

        for(Node n : ln) {
            g.addNode(n);
            Assert.assertTrue(g.findNode(n.getID()).equals(n));
        }
    }

    @Test
    public void testFindNode() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        Assert.assertTrue(g.findNode(0).equals(ln.get(1)));
        Assert.assertTrue(g.findNode(5).equals(ln.get(0)));
        Assert.assertTrue(g.findNode(-2).equals(ln.get(3)));
    }

    @Test
    public void testGetAllNodes() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        Set s = g.getAllNodes();
        for(Node n : ln) {
            Assert.assertTrue(s.contains(n));
        }
    }

    @Test
    public void testGetAllFireNodes() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        Set s = g.getAllFireNodes();
        for(Node n : ln) {
            if(n.getIntensity() > 0) {
                Assert.assertTrue(s.contains(n));
            }
        }
    }

    @Test
    public void testGetNbNodes() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        Assert.assertEquals(ln.size(), g.getNbNodes());
    }

    @Test
    public void testGetNextNodes() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        for(Edge e : le) {
            g.addEdge(e);
        }
        assertTrue(g.getNextNodes(n1).contains(n2));
        assertTrue(g.getNextNodes(n1).contains(n3));
        assertFalse(g.getNextNodes(n1).contains(n4));
        assertFalse(g.getNextNodes(n1).contains(n1));
        assertTrue(g.getNextNodes(n2).contains(n3));
        assertTrue(g.getNextNodes(n2).contains(n1));
    }

    @Test
    public void testHasEdge() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        for(Edge e : le) {
            g.addEdge(e);
        }

        assertTrue(g.hasEdge(n1,n2));
        assertTrue(g.hasEdge(n2,n1));
        assertTrue(g.hasEdge(n5,n6));
        assertFalse(g.hasEdge(n1,n4));
        assertFalse(g.hasEdge(n5,n7));
    }

    @Test
    public void testAddEdge() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        g.addEdge(e1);
        assertTrue(g.getAllEdges().contains(e1));
        assertFalse(g.getAllEdges().contains(e2));
    }

    @Test
    public void testGetSubGraph() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        for(Edge e : le) {
            g.addEdge(e);
        }
        Graph subG1 = new Graph();
        Graph subG2 = new Graph();
        Graph subG3 = new Graph();
        subG1.setNodes(g.getAllNodes());
        subG2.setNodes(g.getAllNodes());
        subG3.setNodes(g.getAllNodes());

        for(Edge e : g.getAllEdges()) {
            if(e.getType().equals(Type.PLAT)) {
                subG1.addEdge(e);
                subG2.addEdge(e);
                subG3.addEdge(e);
            }
            else if(e.getType().equals(Type.INONDE)) {
                subG1.addEdge(e);
                subG2.addEdge(e);
            }
            else if(e.getType().equals(Type.ESCARPE)) {
                subG1.addEdge(e);
                subG3.addEdge(e);
            }
        }

        assertTrue(subG1.equals(g.getSubGraph(1)));
        assertTrue(subG2.equals(g.getSubGraph(2)));
        assertTrue(subG3.equals(g.getSubGraph(3)));
    }

    @Test
    public void testEdgeAuthorized() throws Exception {
        for(Node n : ln) {
            g.addNode(n);
        }
        for(Edge e : le) {
            g.addEdge(e);
        }

        for(Edge e : g.getAllEdges()) {
            if(e.getType().equals(Type.PLAT)) {
                assertTrue(g.edgeAuthorized(e,1));
                assertTrue(g.edgeAuthorized(e,2));
                assertTrue(g.edgeAuthorized(e,3));
            }
            else if(e.getType().equals(Type.INONDE)) {
                assertTrue(g.edgeAuthorized(e,1));
                assertTrue(g.edgeAuthorized(e,2));
                assertFalse(g.edgeAuthorized(e, 3));
            }
            else if(e.getType().equals(Type.ESCARPE)) {
                assertTrue(g.edgeAuthorized(e,1));
                assertFalse(g.edgeAuthorized(e, 2));
                assertTrue(g.edgeAuthorized(e,3));
            }
        }
    }
}