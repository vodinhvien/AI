package HanoiTower;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author Admin
 */

public class HanoiTowerSolver {
    
    private static State createFinalState (int n_pegs, int n_discs) {
        ArrayList<Stack<Integer>> finalBoard = new ArrayList<>();
        for(int i=0;i<n_pegs;i++) {
            Stack<Integer> peg = new Stack<>();
            finalBoard.add(peg);
        }
        for(int i=n_discs;i>0;i--) {
            finalBoard.get(n_pegs-1).add(i);
        }
        State finalState = new State(0, finalBoard, n_pegs, n_discs, null);
        finalState.calcHx();
        return finalState;
    }
    
    public static void solve (List<Stack<Integer>> initialBoard, int n_pegs, int n_discs) {
        PriorityQueue<State> openedList = new PriorityQueue<>();
        List<State> closedList = new LinkedList<>();
        State start = new State(0, initialBoard, n_pegs, n_discs, null);
        System.out.println("Initial state");
        printBoard(start.getBoard());
        System.out.println("Final state");
        State goal = createFinalState(n_pegs, n_discs);
        printBoard(goal.getBoard());
        System.out.println("----------------------");
        start.calcHx();
        openedList.add(start);
        while(!openedList.isEmpty())
        {
            State min = openedList.poll();
            if(min.getHx() == 0)
            {
                printState(min);
                break;
            }
            else {
                ArrayList<State> children = min.generate();
                children.stream().filter(child -> (!closedList.contains(child))).forEachOrdered(child -> {
                    child.calcHx();
                    openedList.add(child);
                });
                closedList.add(min);
            }
        }
    }

    private static void printState(State min) {
        if(min == null)
        {
            //return;
        }
        else {
            printState(min.getParentState());
            System.out.printf("+ Step %d: \n",min.getG());
            printBoard(min.getBoard());
            System.out.printf("g = %d\th = %d\tfx = %d\n",min.getG(),min.getHx(),min.getFx());
            System.out.println("======================");
        }
    }
    
    private static void printBoard(List<Stack<Integer>> board) {
        char temp = 'A';
        for (Stack<Integer> board1 : board) {
            System.out.println(temp + "|"+java.util.Arrays.toString(board1.toArray()));
            temp++;
        }    
    }
    
    public static void main(String... args)
    {
        ArrayList<Stack<Integer>> board = new ArrayList<>();
        Stack<Integer> pegA = new Stack<>();
        Stack<Integer> pegB = new Stack<>();
        Stack<Integer> pegC = new Stack<>();
        
        pegA.push(3);
        pegA.push(2);
        pegA.push(1);
        
        board.add(pegA);
        board.add(pegB);
        board.add(pegC);
        
        HanoiTowerSolver.solve(board, board.size(), 3);
    }

}
