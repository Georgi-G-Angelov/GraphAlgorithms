package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<GraphNode> nodes = new ArrayList<>();

    public void addNote(GraphNode node){
        nodes.add(node);
    }

    public void addTwoWayEdge(String node1, String node2, int distance){
        for(int i=0;i<nodes.size();i++) {
            if(nodes.get(i).name == node1) {
                for(int j=0;j<nodes.size();j++) {
                    if(nodes.get(j).name == node2){
                        nodes.get(i).addNeighbour(nodes.get(j),distance);
                        nodes.get(j).addNeighbour(nodes.get(i),distance);
                        break;
                    }
                }
            }
        }
    }

    public void addOneWayEdge(String node1, String node2, int distance){
        for(int i=0;i<nodes.size();i++) {
            if(nodes.get(i).name == node1) {
                for(int j=0;j<nodes.size();j++) {
                    if(nodes.get(j).name == node2){
                        nodes.get(i).addNeighbour(nodes.get(j),distance);
                        break;
                    }
                }
            }
        }
    }

    public void printDistanceBetween(String node1, String node2){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).name == node1){
                System.out.println(nodes.get(i).name);
                for(int j=0;j<nodes.get(i).neighbours.size();j++){
                    if(nodes.get(i).neighbours.get(j).name == node2){
                        System.out.println(nodes.get(i).neighbours.get(j).name);
                        System.out.println(nodes.get(i).neighbours.get(j).distance);
                        break;
                    }
                }
            }
        }
    }

    public int distanceBetween(String node1, String node2){
        for(int i=0;i<nodes.size();i++){

            if(nodes.get(i).name == node1){

                for(int j=0;j<nodes.get(i).neighbours.size();j++){
                    if(nodes.get(i).neighbours.get(j).name == node2){
                        return nodes.get(i).neighbours.get(j).distance;
                    }
                }

            }

        }
        return 1;
    }

    public GraphNode getNodeByName(String name){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).name == name){
                return nodes.get(i);
            }
        }
        return new GraphNode();
    }

    public int getNodeIndexByName(String name){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).name == name){
                return i;
            }
        }
        return 1;
    }

    public void print(GraphNode startingNode, boolean known[], int cost[], String path[]){
        System.out.println("Starting node: " + startingNode.name);
        System.out.printf("%8.8s","Nodes:" + " | ");
        for(int k=0;k<nodes.size();k++){
            System.out.printf("%17.17s", nodes.get(k).name + " | ");
        }
        System.out.println();
        System.out.printf("%8.8s","Known:" + " | ");
        for(int k=0;k<nodes.size();k++){
            System.out.printf("%17.17s", known[k] + " | ");
        }
        System.out.println();
        System.out.printf("%8.8s","Cost:" + " | ");
        for(int k=0;k<nodes.size();k++){
            System.out.printf("%17.17s", cost[k] + " | ");
        }
        System.out.println();
        System.out.printf("%8.8s","Path:" + " | ");
        for(int k=0;k<nodes.size();k++){
            System.out.printf("%17.17s", path[k] + " | ");
        }
        System.out.println();

    }

    public void Dijkstra(String node){
        boolean known[] = new boolean[nodes.size()];
        int cost[] = new int[nodes.size()];
        String path[] = new String[nodes.size()];
        for(int i=0;i<nodes.size();i++){
            known[i] = false;
            cost[i] = Integer.MAX_VALUE;
            path[i] = "0";
        }
        known[getNodeIndexByName(node)] = true;
        cost[getNodeIndexByName(node)] = 0;
        int min = Integer.MAX_VALUE;
        GraphNode startingNode = getNodeByName(node);
        for(int i=0;i<getNodeByName(node).neighbours.size();i++){
            cost[getNodeIndexByName(getNodeByName(node).neighbours.get(i).name)] = distanceBetween(node, getNodeByName(node).neighbours.get(i).name);
            path[getNodeIndexByName(getNodeByName(node).neighbours.get(i).name)] = node;
            if(distanceBetween(node, getNodeByName(node).neighbours.get(i).name) < min){
                startingNode = getNodeByName(node).neighbours.get(i);
                min = distanceBetween(node, getNodeByName(node).neighbours.get(i).name);
            }
        }
        for(int i=1;i<nodes.size();i++){
             for (int j = 0; j < getNodeByName(startingNode.name).neighbours.size(); j++) {
                    if(distanceBetween(startingNode.name, getNodeByName(startingNode.name).neighbours.get(j).name) + cost[getNodeIndexByName(startingNode.name)] < cost[getNodeIndexByName(getNodeByName(startingNode.name).neighbours.get(j).name)]) {
                        cost[getNodeIndexByName(getNodeByName(startingNode.name).neighbours.get(j).name)] = distanceBetween(startingNode.name, getNodeByName(startingNode.name).neighbours.get(j).name) + cost[getNodeIndexByName(startingNode.name)];
                        path[getNodeIndexByName(getNodeByName(startingNode.name).neighbours.get(j).name)] = startingNode.name;
                    }
                }
                known[getNodeIndexByName(startingNode.name)] = true;
                min = Integer.MAX_VALUE;
                for(int k=0;k<nodes.size();k++){
                    if(cost[k] < min && !known[k]){
                        startingNode = nodes.get(k);
                        min = cost[k];
                    }
                }
        }
        print(getNodeByName(node), known, cost, path);
    }

}
