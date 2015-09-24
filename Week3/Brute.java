public class Brute{
    public static void main(String[] args){
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] P = new Point[N];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            P[i] = new Point(x, y);
            StdDraw.setPenRadius(0.01);
            P[i].draw();
        }
        //StdDraw.show(0);
        java.util.Arrays.sort(P);
        for(int i = 0; i < N - 3; i++){
            for(int j = i + 1; j < N - 2; j++){
                for(int k = j + 1; k < N - 1; k++){
                    for(int r = k + 1; r < N; r++){
                        double jj = P[i].slopeTo(P[j]);
                        double kk = P[i].slopeTo(P[k]);
                        double rr = P[i].slopeTo(P[r]);
                        if(jj == kk && jj == rr){
                            String cad = "";
                            cad += P[i].toString() + " -> " + P[j].toString();
                            cad += " -> ";
                            cad += P[k].toString() + " -> " + P[r].toString();
                            StdOut.println(cad);
                            StdDraw.setPenRadius();
                            P[i].drawTo(P[r]);
                        }
                    }
                }
            }
        }
    }
    //StdDraw.show(0);
    //StdDraw.setPenRadius();
}