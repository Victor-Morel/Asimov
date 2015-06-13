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
        openedList = new ArrayList<SearchNode>();
        closedList = new ArrayList<SearchNode>();

        SearchNode searchNodeStart = new SearchNode(n_start,null,0);
        SearchNode bestSn = null;

        do {
            for(Node n : g.getNextNodes(searchNodeStart.getNode())) {
                SearchNode sn = new SearchNode(n,searchNodeStart,EuclidianDistance.getDistance(n,n_dest));
                int id = -1;
                for(int i = 0; i < closedList.size(); i++) {
                    if(closedList.get(i).getNode().equals(sn.getNode())) {
                        id = i;
                        break;
                    }
                }
                if(id >= 0) {
                    break;
                }
                for(int i = 0; i < closedList.size(); i++) {
                    if(closedList.get(i).getNode().equals(sn.getNode())) {
                        id = i;
                        break;
                    }
                }
                if(id >= 0) {
                    if(sn.getValue() < openedList.get(id).getValue()) {
                        openedList.remove(id);
                        openedList.add(id,sn);
                    }
                }
                else {
                    openedList.add(sn);
                }
            }

            if(openedList.isEmpty()) {
                this.resultGraph = new Graph();
                break;
            }
            bestSn = openedList.get(0);
            for(SearchNode sn : openedList) {
                if (sn.getValue() < bestSn.getValue()) {
                    bestSn = sn;
                }
            }
            openedList.remove(bestSn);
            closedList.add(bestSn);

        }while(bestSn.getNode().equals(n_dest));

        if(bestSn.getNode().equals(n_dest)) {
            this.resultGraph = new Graph();
            resultGraph.addNode(bestSn.getNode());
            SearchNode currentNode = bestSn;
            while(null != currentNode.getParent()) {
                resultGraph.addEdge(g.getEdge(currentNode.getNode(),currentNode.getParent().getNode()));
                resultGraph.addNode(currentNode.getNode());
            }
            resultGraph.addNode(currentNode.getNode());
        }
    }

}
