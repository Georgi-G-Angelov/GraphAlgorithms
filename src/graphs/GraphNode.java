package graphs;

import java.util.ArrayList;

public class GraphNode {
    public String name;
    public int distance;
    public ArrayList<GraphNode> neighbours;

    public GraphNode(){

    }

    public GraphNode(String name){
        this.name = name;
        distance = 0;
        neighbours = new ArrayList<>();
    }

    public GraphNode(String name, int distance){
        this.name = name;
        this.distance = distance;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(GraphNode node, int distance){
        neighbours.add(new GraphNode(node.name, distance));
    }

    public void printNeighbours(){
        for(int i=0;i<this.neighbours.size();i++){
            System.out.println("---");
            System.out.println(neighbours.get(i).name);
            System.out.println(neighbours.get(i).distance);
            System.out.println("---");
        }
    }

}
