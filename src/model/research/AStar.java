package model.research;

import model.graph.Graph;
import model.graph.Node;
import model.graph.SearchNode;
import utils.EuclidianDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 20/05/15.
 */
public class AStar extends Strategy {
    private List<SearchNode> openedList;
    private List<SearchNode> closedList;

    public AStar(Graph g, Node n_start, Node n_dest) {
        this.destination = n_dest;
        this.source = n_start;
        this.graph = g;
        this.openedList = new ArrayList<SearchNode>();
        this.closedList = new ArrayList<SearchNode>();
        this.generateBestPath();
    }

    @Override
    public void generateBestPath() {
        SearchNode currentSearchNode = new SearchNode(source,null,0);
        SearchNode bestSn = null;
        do {
            //System.out.println("\nCurrent children : " + graph.getNextNodes(currentSearchNode.getNode()));
            for(Node n : graph.getNextNodes(currentSearchNode.getNode())) {
                SearchNode sn = new SearchNode(n,currentSearchNode,EuclidianDistance.getDistance(n,destination));
                //System.out.println("Current node : " + sn);
                int id = -1;
                for(int i = 0; i < closedList.size(); i++) {
                    if(closedList.get(i).getNode().equals(sn.getNode())) {
                        id = i;
                        //System.out.println("Break1");
                        break;
                    }
                }
                if(id >= 0) {
                    continue;
                }
                for(int i = 0; i < openedList.size(); i++) {
                    if(openedList.get(i).getNode().equals(sn.getNode())) {
                        id = i;
                        //System.out.println("Break2");
                        break;
                    }
                }
                if(id >= 0) {
                    if(sn.getValue() < openedList.get(id).getValue()) {
                        openedList.remove(id);
                        closedList.add(id,sn);
                    }
                }
                else {
                    openedList.add(sn);
                }
            }

            //System.out.println("OpenedList : " + openedList);
            //System.out.println("ClosedList : " + closedList);
            if(openedList.isEmpty()) {
                this.resultGraph = new Graph();
                //System.out.println("Break3");
                break;
            }
            bestSn = openedList.get(0);
            for(SearchNode sn : openedList) {
                if (sn.getValue() < bestSn.getValue()) {
                    bestSn = sn;
                }
            }
            //System.out.println("BestNode : " + bestSn);
            openedList.remove(bestSn);
            closedList.add(bestSn);

            currentSearchNode = bestSn;
        }while(!bestSn.getNode().equals(destination));

        if(null != bestSn && bestSn.getNode().equals(destination)) {
            this.resultGraph = new Graph();
            getResultGraph().addNode(bestSn.getNode());
            SearchNode currentNode = bestSn;
            while(null != currentNode.getParent()) {
                getResultGraph().addEdge(graph.getEdge(currentNode.getNode(), currentNode.getParent().getNode()));
                getResultGraph().addNode(currentNode.getNode());
                currentNode = currentNode.getParent();
            }
            getResultGraph().addNode(currentNode.getNode());
        }
    }

}
