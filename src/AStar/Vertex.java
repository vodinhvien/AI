package AStar;

/* Import classes and libraries */
import java.util.LinkedList;

public class Vertex {
    
    private int x;
    private int y;
    private LinkedList<Edge> edges;
    
    public Vertex()
    {
        this.x = 0;
        this.y = 0;
        edges = new LinkedList<>();
    }
    public Vertex(int x, int y)
    {
        this.x = x;
        this.y = y;
        edges = new LinkedList<>();
    }
    
    public LinkedList<Edge> getEdges()
    {
        return edges;
    }
    
    public int[] getCoordinates() {
        return new int[] {x,y};
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setCoordinates(int[] coordinates)
    {
        x = coordinates[0];
        y = coordinates[1];
    }
    
    public void setX(int xCoordinate)
    {
        x = xCoordinate;
    }
    
    public void setY(int yCoordinate)
    {
        y = yCoordinate;
    }
    
    public void setEdges(LinkedList<Edge> e)
    {
        this.edges = e;
    }
    
    @Override
    public String toString()
    {
        return String.format("(%d,%d)",x,y);
    }
    
}