import algorithms.BellmanFord;
import graphs.Graph;
import graphs.GraphNode;

import static algorithms.Dijkstra.Dijkstra;
import static algorithms.BellmanFord.BellmanFord;


public class Main {
    public static void main(String args[]){
        Graph Bulgaria = new Graph();
        Bulgaria.addNote(new GraphNode("Sofia"));
        Bulgaria.addNote(new GraphNode("Vraca"));
        Bulgaria.addNote(new GraphNode("Pernik"));
        Bulgaria.addNote(new GraphNode("Plovdiv"));
        Bulgaria.addNote(new GraphNode("Veliko Turnovo"));
        Bulgaria.addNote(new GraphNode("Varna"));
        Bulgaria.addNote(new GraphNode("Burgas"));
        Bulgaria.addNote(new GraphNode("Ruse"));
        Bulgaria.addTwoWayEdge("Sofia","Vraca", 200);
        Bulgaria.addTwoWayEdge("Sofia","Pernik", 50);
        Bulgaria.addTwoWayEdge("Sofia","Veliko Turnovo", 200);
        Bulgaria.addTwoWayEdge("Sofia","Plovdiv", 150);
        Bulgaria.addTwoWayEdge("Veliko Turnovo","Vraca", 200);
        Bulgaria.addTwoWayEdge("Veliko Turnovo","Ruse", 150);
        Bulgaria.addTwoWayEdge("Plovdiv","Burgas", 250);
        Bulgaria.addTwoWayEdge("Burgas","Varna", 100);
        //Bulgaria.addTwoWayEdge("Ruse","Varna",100);


        //Bulgaria.Dijkstra("Ruse");
        Dijkstra("Sofia",Bulgaria);
        BellmanFord("Sofia", Bulgaria);


    }
}
