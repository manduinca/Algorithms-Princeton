public class Fast {
    public static void main(String[] args){
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] P = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            P[i] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        java.util.Arrays.sort(P);
        Point[] Aux = P.clone();
        for(int i = 0; i < N; i++){
            StdDraw.setPenRadius(0.01);
            P[i].draw();
            java.util.Arrays.sort(Aux, P[i].SLOPE_ORDER);
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
                        if(Temp[0].compareTo(P[i]) == 0){
                            String cad = Temp[0].toString();
                            for(int k = 1; k < con + 1; k++)
                                cad += " -> " + Temp[k].toString();
                            StdOut.println(cad);
                            StdDraw.setPenRadius();
                            Temp[0].drawTo(Temp[con]);
                        }
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
                if(Temp[0].compareTo(P[i]) == 0){
                    String cad = Temp[0].toString();
                    for(int k = 1; k < con + 1; k++)
                        cad += " -> " + Temp[k].toString();
                    StdOut.println(cad);
                    StdDraw.setPenRadius();
                    Temp[0].drawTo(Temp[con]);
                }
            }
        }
    }
}