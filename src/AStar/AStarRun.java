package AStar;

/**
 *
 * @author Admin
 */
public class AStarRun {
    
    public static void main(String... args)
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(0,0);
        Vertex v2 = new Vertex(0,1);
        Vertex v3 = new Vertex(0,2);
        Vertex v4 = new Vertex(1,0);
        Vertex v5 = new Vertex(1,1);
        Vertex v6 = new Vertex(1,2);
        Vertex v7 = new Vertex(2,0);
        Vertex v8 = new Vertex(2,1);
        Vertex v9 = new Vertex(2,2);
        
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        graph.addVertex(v6);
        graph.addVertex(v7);
        graph.addVertex(v8);
        graph.addVertex(v9);
        
        graph.addEdge(v1, v2, 2);
        graph.addEdge(v1, v4, 5);
        graph.addEdge(v2, v3, 2);
        graph.addEdge(v3, v6, 1);
        graph.addEdge(v4, v7, 1);
        graph.addEdge(v4, v5, 3);
        graph.addEdge(v5, v6, 1);
        graph.addEdge(v5, v8, 3);
        graph.addEdge(v8, v9, 1);
        
        AStarSolver.solve(graph, v1, v9);
    }
}
