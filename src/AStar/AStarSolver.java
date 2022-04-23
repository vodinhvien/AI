package AStar;

import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarSolver {
    
    public static void solve(Graph graph, Vertex start, Vertex goal)
    {
        graph.printGraph();
        PriorityQueue<Node> openedList = new PriorityQueue<>();
        List<Node> closedList = new LinkedList<>();
        Node startNode = new Node(start, 0, null);
        startNode.calcHx(goal);
        openedList.add(startNode);
        int i = 0;
        System.out.printf("+ Step %d\n", i);
        System.out.println("Opened List: " + java.util.Arrays.toString(openedList.toArray()));
        System.out.println("Closed List: " + java.util.Arrays.toString(closedList.toArray()));
        while(!openedList.isEmpty())
        {
            i++;
            Node min = openedList.poll();
            if(min.getVertex() == goal)
            {
                System.out.printf("+ Step %d\n", i);
                System.out.println(min + " = target node");
                System.out.println("Opened List: " + java.util.Arrays.toString(openedList.toArray()));
                System.out.println("Closed List: " + java.util.Arrays.toString(closedList.toArray()));
                System.out.println("===========================");
                LinkedList<Node> temp = new LinkedList<>();
                getRootNodes(temp, min);
                printPath(temp);
                break;
            }
            else {
                Vertex v = min.getVertex();
                v.getEdges().stream().map(e -> {
                    Vertex dest = e.getDestVertex();
                    Node n = new Node(dest, (int) (min.getDistance() + e.getWeight()), min);
                    n.calcHx(goal);
                    return n;
                }).filter(n -> (!closedList.contains(n)) && !openedList.contains(n)).forEachOrdered(n -> {
                    openedList.add(n);
                });
                closedList.add(min);
                System.out.printf("+ Step %d\n", i);
                System.out.println("Min node: " + min);
                System.out.println("Opened List: " + java.util.Arrays.toString(openedList.toArray()));
                System.out.println("Closed List: " + java.util.Arrays.toString(closedList.toArray()));
            }
        }
    }

    private static void printPath(LinkedList<Node> temp) {
        for(int i=0; i < temp.size(); i++)
        {
            System.out.print(temp.get(i).getVertex());
            if(i < temp.size() - 1)
            {
                System.out.print(" -> ");
            }
        }
        System.out.print("\n");
        System.out.println("Total cost: " + temp.get(temp.size() - 1).calcFx());
    }

    private static void getRootNodes(LinkedList<Node> temp, Node n) {
        if(n == null)
        {
            //return;
        }
        else {
            getRootNodes(temp, n.getPrevNode());
            temp.add(n);
        }
    }
    
}
