package AStar;

import java.util.Objects;

/**
 *
 * @author Kiet
 */
public class Node implements Comparable<Node> {
    
    private Vertex v;
    private Node prevNode;
    
    //The distance from start to the vertex of this node
    private int dist;
    
    private int h;
    
    public Node(Vertex v, int dist, Node prevNode)
    {
        this.v = v;
        this.dist = dist;
        this.prevNode = prevNode;
    }

    @Override
    public int compareTo(Node o) {
        if(this.calcFx() > o.calcFx())
            return 1;
        else if(this.calcFx() < o.calcFx())
            return -1;
        else return 0;
    }
    
    public int getDistance()
    {
        return dist;
    }
    
    public Vertex getVertex()
    {
        return v;
    }
    
    public Node getPrevNode()
    {
        return prevNode;
    }
    
    public void setPrevNode(Node prevNode)
    {
        this.prevNode = prevNode;
    }
    
    public void setDistance(int dist)
    {
        this.dist = dist;
    }
    
    public void setVertex(Vertex v)
    {
        this.v = v;
    }
    
    public int calcFx()
    {
        return dist + h;
    }
    
    public void calcHx(Vertex goal)
    {
        h = Math.abs(goal.getX() - v.getX()) + Math.abs(goal.getY() - v.getY());
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj.getClass() != this.getClass())
        {
            return false;
        }
        else {
            Node n = (Node) obj;
            return n.getVertex().equals(v);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.v);
        return hash;
    }
    
    @Override
    public String toString(){
        return String.format("{%s,%d}",v, calcFx());
    }
}
