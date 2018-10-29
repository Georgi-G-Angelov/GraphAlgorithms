package algorithms;

import graphs.Graph;
import graphs.GraphNode;
import org.jetbrains.annotations.NotNull;

public class Dijkstra {
    public static void Dijkstra(String node, @NotNull Graph graph){
        System.out.println("Dijkstra's algorithm:");
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
        int min = Integer.MAX_VALUE;
        GraphNode startingNode = graph.getNodeByName(node);
        for(int i=0;i<graph.getNodeByName(node).neighbours.size();i++){
            cost[graph.getNodeIndexByName(graph.getNodeByName(node).neighbours.get(i).name)] = graph.distanceBetween(node, graph.getNodeByName(node).neighbours.get(i).name);
            path[graph.getNodeIndexByName(graph.getNodeByName(node).neighbours.get(i).name)] = node;
            if(graph.distanceBetween(node, graph.getNodeByName(node).neighbours.get(i).name) < min){
                startingNode = graph.getNodeByName(node).neighbours.get(i);
                min = graph.distanceBetween(node, graph.getNodeByName(node).neighbours.get(i).name);
            }
        }
        for(int i=1;i<graph.nodes.size();i++){
            for (int j = 0; j < graph.getNodeByName(startingNode.name).neighbours.size(); j++) {
                if(graph.distanceBetween(startingNode.name, graph.getNodeByName(startingNode.name).neighbours.get(j).name) + cost[graph.getNodeIndexByName(startingNode.name)] < cost[graph.getNodeIndexByName(graph.getNodeByName(startingNode.name).neighbours.get(j).name)]) {
                    cost[graph.getNodeIndexByName(graph.getNodeByName(startingNode.name).neighbours.get(j).name)] = graph.distanceBetween(startingNode.name, graph.getNodeByName(startingNode.name).neighbours.get(j).name) + cost[graph.getNodeIndexByName(startingNode.name)];
                    path[graph.getNodeIndexByName(graph.getNodeByName(startingNode.name).neighbours.get(j).name)] = startingNode.name;
                }
            }
            known[graph.getNodeIndexByName(startingNode.name)] = true;
            min = Integer.MAX_VALUE;
            for(int k=0;k<graph.nodes.size();k++){
                if(cost[k] < min && !known[k]){
                    startingNode = graph.nodes.get(k);
                    min = cost[k];
                }
            }
        }
        graph.print(graph.getNodeByName(node), known, cost, path);
    }
}
