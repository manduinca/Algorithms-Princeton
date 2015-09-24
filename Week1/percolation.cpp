#include <iostream>
#include <fstream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

class Percolation{
    vector <bool> M;
    vector <int> id;
    int N;
    bool connected(int p, int q){
        return id[p] == id[q];
    }
    void Union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < id.size(); i++)
            if(id[i] == pid) id[i] = qid;
    }
public:
    Percolation(int T){
        N = T;
        for(int i = 0; i < N * N; i++){
            M.push_back(0);
            id.push_back(i);
        }
    }
    bool isOpen(int i, int j){ return M[i * N + j];}
    void open(int i, int j){
        M[i * N + j] = 1;
        if(i >= 1)
            if(isOpen(i - 1, j)) Union(i * N + j, (i - 1) * N + j);
        if(j >= 1)
            if(isOpen(i, j - 1)) Union(i * N + j, i * N + j - 1);
        if(j <= N - 2)
            if(isOpen(i, j + 1)) Union(i * N + j, i * N + j + 1);
        if(i <= N - 2)
            if(isOpen(i + 1, j)) Union(i * N + j, (i + 1) * N + j);
    }
    bool isFull(int i, int j){
        if(isOpen(i, j))
            for(int k = 0; k < N; k++)
                if(connected(i * N + j, k)) return 1;
        return 0;
    }
    bool percolates(){
        for(int j = 0; j < N; j++)
            if(isFull(N - 1, j)) return 1;
        return 0;
    }
    void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                cout << M[i * N + j] << " ";
            cout << endl;
        }
        cout << endl;
    }
};

class PercolationStats{
    vector <double> X;
    vector <int> A;
    int N, T;
    int aleatorio(){
        int n = rand() % A.size();
        int a = A[n];
        A.erase(A.begin() + n);
        return a;
    }
    void calcularX(){
        srand(time(NULL));
        for(int i = 0; i < T; i++){
            Percolation P(N);
            for(int j = 0; j < N * N; j++)
                A.push_back(j);
            while(!P.percolates()){
                int n = aleatorio();
                P.open(n / N, n % N);
            }
            X.push_back(1.0 - (double) A.size() / (N * N));
            A.clear();
        }
    }
public:
    PercolationStats(int _N, int _T){
        N = _N; T = _T;
        calcularX();
    }
    double mean(){
        double sum = 0;
        for(int i = 0; i < X.size(); i++)
            sum += X[i];
        return sum / T;
    }
    double stddev(){
        double media = mean(), o2 = 0;
        for(int i = 0; i < X.size(); i++)
            o2 += (X[i] - media) * (X[i] - media);
        return o2 / (T - 1);
    }
    double confidenceLo(){
        return mean() - 1.96 * sqrt(stddev() / T);
    }
    double confidenceHi(){
        return mean() + 1.96 * sqrt(stddev() / T);
    }
};

int main(){
    PercolationStats PS(200, 10);
    cout << "mean                    = " << PS.mean() <<  endl;
    cout << "stddev                  = " << PS.stddev() <<  endl;
    cout << "95% confidence interval = " << PS.confidenceLo() << ", " << PS.confidenceHi() <<  endl;
    return 0;
}
