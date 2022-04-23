package HanoiTower;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author Kiet
 */

public class State implements Comparable<State>{
    
    private int g;
    private int h = Integer.MAX_VALUE;
    private List<Stack<Integer>> board;
    private State parent;
    
    private int n_pegs = DEFAULT_N_PEGS;
    private int n_discs = DEFAULT_N_DISCS;
    
    public static final int DEFAULT_N_PEGS = 3;
    public static final int DEFAULT_N_DISCS = 3;
    
    public State()
    {
        this.g = 0;
        this.parent = null;
        this.board = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            board.add(new Stack<>());
        }
        for(int i = 3; i > 0; i--)
        {
            board.get(0).push(i);
        }
    }
    
    public State(int g, List<Stack<Integer>> board, int n_pegs, int n_discs, State parent)
    {
        this.g = g;
        this.parent = parent;
        this.n_discs = n_discs;
        this.n_pegs = n_pegs;
        this.board = new ArrayList<>(board.size());
        for(int i=0;i<n_pegs;i++)
        {
            this.board.add(i, new Stack<>());
            this.board.get(i).addAll(board.get(i));
        }
    }
    
    public int getFx()
    {
        return g + h;
    }
    
//    public void calcHx() {
//        /**
//         *  Hàm dự đoán các trạng thái thứ tự đĩa sẽ xảy ra với heuristic tương ứng (n=3) 
//         *  Reference: https://bit.ly/3LUiKMR
//         */
//        int[][] givenHeuristic = {{3, 2, 1}, //h = 0
//                                  {3, 2},    //h = 1
//                                  {3},       //h = 2 
//                                  {3, 1},    //h = 3
//                                  {},        //h = 4
//                                  {1},       //h = 5
//                                  {2},       //h = 6
//                                  {2, 1}};   //h = 7
//        int[] column = board.get(board.size()-1).stream().mapToInt(Integer::intValue).toArray();
//        for(int i=0;i<column.length;i++)
//        {
//            if(java.util.Arrays.equals(column, givenHeuristic[i]))
//            {
//                h = i;
//            }
//        }
//        h = Integer.MAX_VALUE;
//    }
    
    public void calcHx() {
        int lastPegIndex = board.size() - 1;
        int[] column = board.get(lastPegIndex).stream().mapToInt(Integer::intValue).toArray();
        int n = n_discs;
        int m = 0; //Số đĩa đặt đúng vị trí ở cột cuối
        int k = 0; //Số đĩa đặt sai vị trí ở cột cuối
        for(int i=0;i<column.length;i++)
        {
            int discSize = column[i];
            int expectedDiscAt = n - discSize;
            if(i == expectedDiscAt)
            {
                m = m + 1;
            }
            else {
                k = k + 1;
            }
        }
        h = n - m + k;
    }

    public void calcHx(State goal) {
        //Không định nghĩa hàm thì xoá function này
    }
    
    public ArrayList<State> generate()
    {
        ArrayList<State> newStates = new ArrayList<>();
        for(int i=0;i<n_pegs;i++)
        {
            for(int j=0;j<n_pegs;j++)
            {
                if(i != j)
                {
                    if(!board.get(i).empty() && board.get(j).empty())
                    {
                        State child = new State(g+1, board, n_pegs, n_discs, this);
                        child.movePlate(i,j);
                        newStates.add(child);
                    }
                    else if (!board.get(i).empty() && !board.get(j).empty()){
                        int num1 = board.get(i).peek();
                        int num2 = board.get(j).peek();
                        if(num1 > num2) {
                            //It gonna be the bigger plate will on top of the smaller one
                            //Do nothing
                        }
                        else {
                            State child = new State(g+1, board, n_pegs, n_discs, this);
                            child.movePlate(i,j);
                            newStates.add(child);
                        }
                    }
                }
            }
        }
        return newStates;
    }
    
    
    public void setG(int g)
    {
        this.g = g;
    }
    
    public void setParentState(State parent)
    {
        this.parent = parent;
    }
    
    public State getParentState()
    {
        return parent;
    }
    
    public int getHx()
    {
        return h;
    }
    
    public int getG()
    {
        return g;
    }
    
    public List<Stack<Integer>> getBoard()
    {
        return board;
    }
    
    @Override
    public int compareTo(State o) {
        return (this.getFx() - o.getFx());
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        else {
            State obj = (State) o;
            return board.equals(obj.getBoard());
        }
    }

    public void setBoard(List<Stack<Integer>> board) 
    {
        this.board = board;
    }
    
    public void setDiscsLength(int discsLength)
    {
        this.n_discs = discsLength;
    }
    
    public void setPegsLength(int pegsLength)
    {
        this.n_pegs = pegsLength;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.board);
        return hash;
    }

    private void movePlate(int currentPeg, int destinationPeg) {
        int number = board.get(currentPeg).pop();
        board.get(destinationPeg).push(number);
    }
    
}
