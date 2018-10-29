package algorithms;

import graphs.Graph;
import graphs.GraphNode;
import org.jetbrains.annotations.NotNull;

public class BellmanFord {
    public static void BellmanFord(String node, @NotNull Graph graph){
        System.out.println("Bellman-Ford algorithm:");
        boolean known[] = new boolean[graph.nodes.size()];
        int cost[] = new int[graph.nodes.size()];
        String path[] = new String[graph.nodes.size()];
        for(int i=0;i<graph.nodes.size();i++){
            known[i] = false;
            cost[i] = Integer.MAX_VALUE;
            path[i] = "0";
        }
        known[graph.getNodeIndexByName(node)] = true;
        cost[graph.getNodeIndexByName(node)] = 0;

        GraphNode startingNode = graph.getNodeByName(node);
        int startingNodeIndex = graph.getNodeIndexByName(node);
        GraphNode currentNode = new GraphNode();

        for (int i = 0; i < startingNode.neighbours.size(); i++) {
            cost[graph.getNodeIndexByName(graph.getNodeByName(node).neighbours.get(i).name)] = graph.distanceBetween(node, graph.getNodeByName(node).neighbours.get(i).name);
            path[graph.getNodeIndexByName(graph.getNodeByName(node).neighbours.get(i).name)] = node;
        }

        for(int m=0;m<graph.nodes.size()+2;m++) {
            boolean hasChanged = false;
            for (int i = 0; i < graph.nodes.size(); i++) {
                    currentNode = graph.nodes.get(i);
                    for (int j = 0; j < currentNode.neighbours.size(); j++) {
                        if (graph.distanceBetween(currentNode.name, currentNode.neighbours.get(j).name) + cost[i] < cost[graph.getNodeIndexByName(currentNode.neighbours.get(j).name)] && cost[i]!=Integer.MAX_VALUE) {
                            cost[graph.getNodeIndexByName(currentNode.neighbours.get(j).name)] = graph.distanceBetween(currentNode.name, currentNode.neighbours.get(j).name) + cost[i];
                            path[graph.getNodeIndexByName(currentNode.neighbours.get(j).name)] = currentNode.name;
                            hasChanged = true;
                        }
                    }
                    known[graph.getNodeIndexByName(currentNode.name)] = true;
            }
            if(!hasChanged){
                break;
            }
        }
        graph.print(graph.getNodeByName(node), known, cost, path);
    }
}
