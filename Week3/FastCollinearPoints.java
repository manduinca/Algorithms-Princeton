import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList <LineSegment> AL = new ArrayList <LineSegment>();
    private final Point[] P;
    public FastCollinearPoints(Point[] PP) {
        P = new Point[PP.length];
        for(int i = 0; i < PP.length; i++) P[i] = PP[i];
        if(P == null) throw new java.lang.NullPointerException();

        int N = P.length;
        java.util.Arrays.sort(P);

        for(int i = 0; i < N - 1; i++){
            if(P[i] == null) throw new java.lang.NullPointerException();
            if(P[i].compareTo(P[i + 1]) == 0) throw new java.lang.IllegalArgumentException();
        }
        if(P[N - 1] == null) throw new java.lang.NullPointerException();

        Point[] Aux = P.clone();
        for(int i = 0; i < N; i++){
            java.util.Arrays.sort(Aux, P[i].slopeOrder());
            int con = 1;
            boolean band = false;
            for(int j = 1; j < N; j++){
                if(Aux[0].slopeTo(Aux[j]) == Aux[0].slopeTo(Aux[j - 1])) con++;
                else{
                    if(con >= 3){
                        Point[] Temp = new Point[con + 1];
                        Temp[0] = Aux[0];
                        for(int k = j - 1; k >= j - con; k--)
                            Temp[k - j + con + 1] = Aux[k];
                        java.util.Arrays.sort(Temp);
                        if(Temp[0].compareTo(P[i]) == 0)
                            AL.add(new LineSegment(Temp[0], Temp[con]));
                    }
                    con = 1;
                }
            }
            if(con >= 3){
                Point[] Temp = new Point[con + 1];
                Temp[0] = Aux[0];
                for(int k = N - 1; k >= N - con; k--)
                    Temp[k - N + con + 1] = Aux[k];
                java.util.Arrays.sort(Temp);
                if(Temp[0].compareTo(P[i]) == 0)
                    AL.add(new LineSegment(Temp[0], Temp[con]));
            }
        }
    }
    public int numberOfSegments() {
        return AL.size();
    }
    public LineSegment[] segments() {
        LineSegment[] LS = new LineSegment[AL.size()];
        for(int i = 0; i < AL.size(); i++) LS[i] = AL.get(i);
        return LS;
    }
    public static void main(String[] args){
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] P = new Point[N];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        for(int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            P[i] = new Point(x, y);
            P[i].draw();
        }
        FastCollinearPoints FP = new FastCollinearPoints(P);
        StdDraw.setPenRadius(0.002);
        for(LineSegment segment : FP.segments()){
            StdOut.println(segment.toString());
            segment.draw();
        }
    }
}
