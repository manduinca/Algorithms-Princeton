import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats{
    private int N, T;
    private double X[];
    private Percolation P;
    private int random(){
        int n = 1 + StdRandom.uniform(N * N);
        return n;
    }
    private void calcularX(){
        for(int i = 0; i < T; i++){
            P = new Percolation(N);
            int j = 0;
            while(!P.percolates()){
                int n = random();
                int a = n / N + 1, b = n % N;
                if(n % N == 0){a = n / N; b = N;}
                if(!P.isOpen(a, b)) P.open(a, b);
                else j--;
                j++;
            }
            X[i] = (double) j / (N * N);
            P = null;
        }
    }
    public PercolationStats(int _N, int _T){
        if (_N <= 0 || _T <= 0) {
            throw new IllegalArgumentException();
        }
        N = _N; T = _T;
        X = new double[T];
        calcularX();
    }
    public double mean(){
        return StdStats.mean(X);
    }
    public double stddev(){
        return StdStats.stddev(X);
    }
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats Ps = new PercolationStats(N, T);
        double m = Ps.mean(), s = Ps.stddev();
        double lo = Ps.confidenceLo(), hi = Ps.confidenceHi();
        System.out.println("mean                    = " + m);
        System.out.println("stddev                  = " + s);
        System.out.println("95% confidence interval = " + lo + ", " + hi);
    }
}
