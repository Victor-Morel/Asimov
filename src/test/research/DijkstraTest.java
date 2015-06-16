package test.research;

import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.TypeEdge;
import model.research.AStar;
import model.research.Dijkstra;
import model.research.Strategy;
import org.junit.Before;
import org.junit.Test;
import utils.EuclidianDistance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Logan Paul on 13/06/2015.
 */
public class DijkstraTest {

    Graph g;
    Node n0;
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
    Edge e5;
    Edge e6;
    Edge e7;
    List<Node> ln;
    List<Edge> le;

    @Before
    public void setUp() {
        g = new Graph();
        n0 = new Node(0,0);
        n1 = new Node(1.0,0.0);
        n2 = new Node(2.0,3.0,0);
        n3 = new Node(2.5,3.5,120);
        n4 = new Node(3.0,3.0,0);
        n5 = new Node(6.0,5.0,1);
        n6 = new Node(0.0,1.0,Integer.MAX_VALUE - 1);
        n7 = new Node(1.0,0.0);
        e1 = new Edge(n0, n1, EuclidianDistance.getDistance(n1, n2), TypeEdge.PLAT);
        e2 = new Edge(n1, n2, EuclidianDistance.getDistance(n1, n3), TypeEdge.ESCARPE);
        e3 = new Edge(n2, n3, EuclidianDistance.getDistance(n2, n3), TypeEdge.PLAT);
        e4 = new Edge(n3, n4, EuclidianDistance.getDistance(n5, n6), TypeEdge.INONDE);
        e5 = new Edge(n0, n6, EuclidianDistance.getDistance(n5, n6), TypeEdge.INONDE);
        e6 = new Edge(n6, n5, EuclidianDistance.getDistance(n5, n6), TypeEdge.INONDE);
        e7 = new Edge(n5, n4, EuclidianDistance.getDistance(n5, n6), TypeEdge.INONDE);
        ln = new ArrayList<Node>();
        le = new ArrayList<Edge>();
        ln.add(n0);
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
        le.add(e5);
        le.add(e6);
        le.add(e7);
        for(Node n : ln) {
            g.addNode(n);
        }
        for(Edge e : le) {
            g.addEdge(e);
        }
    }

    @Test
    public void testGenerateBestPath() {
        Graph gRes = new Graph();
        gRes.addNode(n0);
        gRes.addNode(n1);
        gRes.addNode(n2);
        gRes.addNode(n3);
        gRes.addNode(n4);
        gRes.addEdge(e1);
        gRes.addEdge(e2);
        gRes.addEdge(e3);
        gRes.addEdge(e4);
        Strategy s = new Dijkstra(g,n0,n4);
        assertTrue(gRes.equals(s.getResultGraph()));

        s = new Dijkstra(g,n2,n5);
        assertFalse(gRes.equals(s.getResultGraph()));

        s = new Dijkstra(g,n1,n7);
        assertTrue(s.getResultGraph().equals(new Graph()));
    }
}