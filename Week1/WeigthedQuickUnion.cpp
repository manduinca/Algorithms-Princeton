#include <iostream>
#include <vector>

using namespace std;

class WQuickUnionUF{
    vector <int> id, sz;
public:
    WQuickUnionUF(int N){
        for(int i = 0; i < N; i++){
            id.push_back(i);
            sz.push_back(1);
        }
    }
    int root(int i){
        while(i != id[i]){
            //id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    bool connected(int p, int q){
        return root(p) == root(q);
    }
    void Union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] < sz[j]) {id[i] = j; sz[j] += sz[i];}
        else {id[j] = i; sz[i] += sz[j];}
    }
    void print(){
        for(int i = 0; i < id.size(); i++)
            cout << id[i] << " ";
        cout << endl;
    }
};

int main(){
    WQuickUnionUF QF(10);
    QF.print();
    cout << "1 - 8\n";
    QF.Union(5, 1);
    QF.print();
    cout << "5 - 9\n";
    QF.Union(4, 7);
    QF.print();
    cout << "4 - 5\n";
    QF.Union(8, 0);
    QF.print();
    cout << "3 - 9\n";
    QF.Union(4, 6);
    QF.print();
    cout << "0 - 6\n";
    QF.Union(1, 8);
    QF.print();
    cout << "3 - 2\n";
    QF.Union(7, 3);
    QF.print();
    cout << "0 - 1\n";
    QF.Union(2, 7);
    QF.print();
    cout << "2 - 8\n";
    QF.Union(2, 9);
    QF.print();
    cout << "0 - 7\n";
    QF.Union(3, 5);
    QF.print();
    return 0;
}
