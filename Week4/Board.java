import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

public class Board {
    private int[][] board;
    private int N;

    public Board(int[][] blocks) {
        N = blocks[0].length;
        board = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                board[i][j] = blocks[i][j];
    }
    private int goal(int i, int j) {
        if(i * N + j < N * N -1)
            return i * N + j + 1;
        return 0;
    }
    public int dimension() {
        return N;
    }
    public int hamming() {
        int out = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                if(goal(i, j) != 0)
                    if(goal(i, j) != board[i][j])
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
                if(goal(i, j) != board[i][j]) return false;
        return true;
    }
    public Board twin() {
        int ri = StdRandom.uniform(N), rj = StdRandom.uniform(N);
        while(board[ri][rj] == 0) {
            ri = StdRandom.uniform(N);
            rj = StdRandom.uniform(N);
        }
        int rx = StdRandom.uniform(N), ry = StdRandom.uniform(N);
        while(board[rx][ry] == 0 || (rx == ri && ry == rj)) {
            rx = StdRandom.uniform(N);
            ry = StdRandom.uniform(N);
        }
        int aux = board[ri][rj];
        board[ri][rj] = board[rx][ry];
        board[rx][ry] = aux;
        Board btwin = new Board(board);
        aux = board[ri][rj];
        board[ri][rj] = board[rx][ry];
        board[rx][ry] = aux;
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
            board[ii][jj] = board[ii - 1][jj];
            board[ii - 1][jj] = 0;
            pila.enqueue(new Board(board));
            board[ii - 1][jj] = board[ii][jj];
            board[ii][jj] = 0;
        }
        if(jj - 1 >= 0) {
            board[ii][jj] = board[ii][jj - 1];
            board[ii][jj - 1] = 0; 
            pila.enqueue(new Board(board));
            board[ii][jj - 1] = board[ii][jj];
            board[ii][jj] = 0; 
        }
        if(ii + 1 < N) {
            board[ii][jj] = board[ii + 1][jj];
            board[ii + 1][jj] = 0; 
            pila.enqueue(new Board(board));
            board[ii + 1][jj] = board[ii][jj];
            board[ii][jj] = 0; 
        }
        if(jj + 1 < N) {
            board[ii][jj] = board[ii][jj + 1];
            board[ii][jj + 1] = 0; 
            pila.enqueue(new Board(board));
            board[ii][jj + 1] = board[ii][jj];
            board[ii][jj] = 0; 
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
