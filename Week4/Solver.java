import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Comparator;
import java.lang.NullPointerException;

public class Solver {
    private int N;
    private boolean solve;
    private Node sol = null;
    private class Node {
        int n = 0;
        Board board;
        Node previous = null;
        public Node(Board b) {
            board = b;
        }
    }
    private class CompManhattan implements Comparator <Node> {
        public int compare(Node x, Node y) {
            if(x.board.manhattan() + x.n > y.board.manhattan() + y.n) return 1;
            else
                if(x.board.manhattan() + x.n < y.board.manhattan() + y.n) return -1;
                else return 0;
        }
    }
    private Comparator <Node> comparator() {
        return new CompManhattan();
    }
    private void isSolver(Board initial) {
        MinPQ <Node> cola = new MinPQ(comparator());
        MinPQ <Node> colatwin = new MinPQ(comparator());
        Node head = new Node(initial);
        Node headtwin = new Node(initial.twin());
        cola.insert(head);
        colatwin.insert(headtwin);
        boolean ban = false;
        while(true) {
            Node min = cola.min();
            if(min.board.isGoal()) {sol = min; N = min.n; ban = true; break;}
            cola.delMin();
            //StdOut.println("min-->" + min.board.toString());
            for(Board i : min.board.neighbors()) {
                if(min.previous != null && i.equals(min.previous.board)) continue;
                Node ii = new Node(i);
                ii.previous = min;
                ii.n = min.n + 1;
                cola.insert(ii);
            }
            min = colatwin.min();
            if(min.board.isGoal()) break;
            colatwin.delMin();
            //StdOut.println("mintwin-->" + min.board.toString());
            for(Board i : min.board.neighbors()) {
                if(min.previous != null && i.equals(min.previous.board)) continue;
                Node ii = new Node(i);
                ii.previous = min;
                ii.n = min.n + 1;
                colatwin.insert(ii);
            }
        }
        if(ban) solve = true;
        else solve = false;
    }
    public Solver(Board initial) {
        if(initial == null) throw new java.lang.NullPointerException();
        isSolver(initial); 
    }
    public boolean isSolvable() {
        return solve;
    }
    public int moves() {
        if(isSolvable() == false) return -1;
        return N;
    }
    public Iterable <Board> solution() {
        if(!solve) return null;
        Stack <Board> pila = new Stack();
        //mantenemos sol apuntando al nodo solución
        //por si se ralizan múltiples llamadas a solution()
        Node tempsol = sol;
        while(tempsol != null) {
            pila.push(tempsol.board);
            tempsol = tempsol.previous;
        }
        return pila;
    }
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        
        if(!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for(Board board : solver.solution())
                StdOut.println(board.toString());
        }
    }
}
