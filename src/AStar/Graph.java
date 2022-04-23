package AStar;

/*Import classes and libraries*/
import java.util.ArrayList;
import java.util.LinkedList;
/**
 *
 * @author Kiet
 */

public class Graph {
    
    /* This graph class uses the edge list to represent the edge of two vertices */
    private ArrayList<Vertex> vertices;
    
    public Graph()
    {
        vertices = new ArrayList<>();
    }
    
    public Graph(ArrayList<Vertex> vertices)
    {
        this.vertices = vertices;
    }
    
    public void addVertex(Vertex v)
    {
        vertices.add(v);
    }
    
    public boolean addEdge(Vertex v1, Vertex v2, int weight)
    {
        return v1.getEdges().add(new Edge(v2, weight)) && v2.getEdges().add(new Edge(v1, weight));
    }
    
    public ArrayList<Vertex> getVertices()
    {
        return vertices;
    }
    
    public void setVertexes(ArrayList<Vertex> vertices)
    {
        this.vertices = vertices;
    }
    
    public LinkedList<Edge> getEdges(Vertex v)
    {
        for(int i=0;i<vertices.size();i++)
        {
            if(vertices.get(i).equals(v))
            {
                return vertices.get(i).getEdges();
            }
        }
        return new LinkedList<>();
    }
    
    public void printGraph() {
        System.out.println("*********Edge List*********");
        vertices.stream().map(v -> {
            v.getEdges().forEach(e -> {
                System.out.print(v);
                System.out.println(e.getDestVertex() + " \t" + e.getWeight());
            });
            return v;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
        System.out.println("***************************");
    }
     
}
