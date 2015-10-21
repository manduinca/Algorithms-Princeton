import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private boolean M[][];
    private int N;
    private WeightedQuickUnionUF UF, UF2;
    public Percolation(int T){
        if(T <= 0) throw new IllegalArgumentException();
        N = T;
        M = new boolean[N + 1][N + 1];
        UF = new WeightedQuickUnionUF(N * N + 2);
        UF2 = new WeightedQuickUnionUF(N * N + 1);
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                M[i][j] = false;
    }
    public boolean isOpen(int i, int j){
        if(i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        return M[i][j];
    }
    public void open(int i, int j){
        if(i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        M[i][j] = true;
        if(i > 1)
            if(isOpen(i - 1, j)){
                UF.union((i - 1) * N + j, (i - 2) * N + j);
                UF2.union((i - 1) * N + j, (i - 2) * N + j);
            }
        if(j > 1)
            if(isOpen(i, j - 1)){
                UF.union((i - 1) * N + j, (i - 1) * N + j - 1);
                UF2.union((i - 1) * N + j, (i - 1) * N + j - 1);
            }
        if(j <= N - 1)
            if(isOpen(i, j + 1)){
                UF.union((i - 1) * N + j, (i - 1) * N + j + 1);
                UF2.union((i - 1) * N + j, (i - 1) * N + j + 1);
            }
        if(i <= N - 1)
            if(isOpen(i + 1, j)){
                UF.union((i - 1) * N + j, i * N + j);
                UF2.union((i - 1) * N + j, i * N + j);
            }
        if(i == N) UF.union((N - 1) * N + j, N * N + 1);
        if(i == 1){UF.union(j, 0); UF2.union(j, 0);}
    }   
    public boolean isFull(int i, int j){
        if(i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        if(isOpen(i, j))
            if(UF2.connected(0, (i - 1) * N + j)) return true;
        return false;
    }
    public boolean percolates(){
        if(UF.connected(0, N * N + 1)) return true;
        return false;
    }
}
