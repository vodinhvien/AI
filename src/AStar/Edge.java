package AStar;

/**
 *
 * @author Kiet
 */

public class Edge {
    
    private int weight;
    private Vertex destVertex;
    
    public Edge()
    {
        weight = Integer.MAX_VALUE;
        destVertex = null;
    }
    
    public Edge(Vertex node)
    {
        destVertex = node;
        weight = Integer.MAX_VALUE;
    }
    
    public Edge(Vertex node, int w)
    {
        this.destVertex = node;
        this.weight = w;
    }
    
    public Vertex getDestVertex()
    {
        return destVertex;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    public void setDestVertex(Vertex v)
    {
        this.destVertex = v;
    }
    
    public void setWeight(int w)
    {
        this.weight = w;
    }
    
}