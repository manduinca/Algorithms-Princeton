import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList <LineSegment> AL = new ArrayList <LineSegment>();
    private final Point[] P;
    public BruteCollinearPoints(Point[] PP) {
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
        
        for(int i = 0; i < N - 3; i++) {
            for(int j = i + 1; j < N - 2; j++) {
                for(int k = j + 1; k < N - 1; k++) {
                    for(int r = k + 1; r < N; r++) {
                        double jj = P[i].slopeTo(P[j]);
                        double kk = P[i].slopeTo(P[k]);
                        double rr = P[i].slopeTo(P[r]);
                        if(jj == kk && jj == rr)
                            AL.add(new LineSegment(P[i], P[r]));
                    }
                }
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

    public static void main(String[] args) {
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
        BruteCollinearPoints BP = new BruteCollinearPoints(P);
        StdDraw.setPenRadius(0.002);
        for(LineSegment segment : BP.segments()) {
            StdOut.println(segment.toString());
            segment.draw();
        }    
    }
}
