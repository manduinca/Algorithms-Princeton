#include <iostream>
#include <vector>

using namespace std;

class QuickFindUF{
    vector <int> id;
public:
    QuickFindUF(int N){
        for(int i = 0; i < N; i++)
            id.push_back(i);
    }
    bool connected(int p, int q){
        return id[p] == id[q];
    }
    void Union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for(int i = 0; i < id.size(); i++)
            if(id[i] == pid) id[i] = qid;
    }
    void print(){
        for(int i = 0; i < id.size(); i++)
            cout << id[i] << " ";
        cout << endl;
    }
};

int main(){
    QuickFindUF QF(10);
    QF.print();
    cout << "9 - 3\n";
    QF.Union(1, 4);
    QF.print();
    cout << "6 - 0\n";
    QF.Union(3, 7);
    QF.print();
    cout << "2 - 1\n";
    QF.Union(0, 3);
    QF.print();
    cout << "9 - 0\n";
    QF.Union(7, 9);
    QF.print();
    cout << "8 - 9\n";
    QF.Union(9, 8);
    QF.print();
    cout << "8 - 1\n";
    QF.Union(5, 8);
    QF.print();
    return 0;
}
