import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

public class Board {
    private int[][] board;
    private int[][] goal;
    private int[][] tempboard;
    private int N;

    public Board(int[][] blocks) {
        N = blocks[0].length;
        goal = new int[N][N];
        board = new int[N][N];
        tempboard = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
                goal[i][j] = i * N + j + 1;
            }
        goal[N - 1][N - 1] = 0;
    }
    public int dimension() {
        return N;
    }
    public int hamming() {
        int out = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(goal[i][j] != 0)
                    if(goal[i][j] != board[i][j])
                        out++;
        return out;
    }
    private int abs(int a, int b) {
        if(a > b) return a - b;
        else return b - a;
    }
    public int manhattan() {
        int sum = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(board[i][j] != 0)
                    sum += abs(i, (board[i][j] - 1) / N) +
                           abs(j, board[i][j] - (board[i][j] - 1) / N * N - 1);
        return sum;
    }
    public boolean isGoal() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(goal[i][j] != board[i][j]) return false;
        return true;
    }
    public Board twin() {
        int[][] boardtwin = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                boardtwin[i][j] = board[i][j];
        int ri = StdRandom.uniform(N), rj = StdRandom.uniform(N);
        while(boardtwin[ri][rj] == 0) {
            ri = StdRandom.uniform(N);
            rj = StdRandom.uniform(N);
        }
        int rx = StdRandom.uniform(N), ry = StdRandom.uniform(N);
        while(boardtwin[rx][ry] == 0 || (rx == ri && ry == rj)) {
            rx = StdRandom.uniform(N);
            ry = StdRandom.uniform(N);
        }
        int aux = boardtwin[ri][rj];
        boardtwin[ri][rj] = boardtwin[rx][ry];
        boardtwin[rx][ry] = aux;
        Board btwin = new Board(boardtwin);
        return btwin;
    }
    public boolean equals(Object x) {
        if(x == this) return true;
        if(x == null) return false;
        if(x.getClass() != this.getClass()) return false;
        Board y = (Board) x;
        if(N != y.N) return false;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(board[i][j] != y.board[i][j]) return false;
        return true;
    }
    private void copyBoard() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                tempboard[i][j] = board[i][j];
    }
    public Iterable <Board> neighbors() {
        int ii = 0, jj = 0;
        boolean band = false;
        Queue <Board> pila = new Queue();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                if(board[i][j] == 0) {ii = i; jj = j; band = true; break;}
            if(band) break;
        }
        if(ii - 1 >= 0) {
            copyBoard();
            tempboard[ii][jj] = tempboard[ii - 1][jj];
            tempboard[ii - 1][jj] = 0; 
            pila.enqueue(new Board(tempboard));
        }
        if(jj - 1 >= 0) {
            copyBoard();
            tempboard[ii][jj] = tempboard[ii][jj - 1];
            tempboard[ii][jj - 1] = 0; 
            pila.enqueue(new Board(tempboard));
        }
        if(ii + 1 < N) {
            copyBoard();
            tempboard[ii][jj] = tempboard[ii + 1][jj];
            tempboard[ii + 1][jj] = 0; 
            pila.enqueue(new Board(tempboard));
        }
        if(jj + 1 < N) {
            copyBoard();
            tempboard[ii][jj] = tempboard[ii][jj + 1];
            tempboard[ii][jj + 1] = 0; 
            pila.enqueue(new Board(tempboard));
        }
        return pila;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N - 1; j++)
                s.append(board[i][j] + " ");
            s.append(board[i][N - 1] + "\n");
        }
        return s.toString(); 
    }
    public static void main(String[] args) {
    }
}
