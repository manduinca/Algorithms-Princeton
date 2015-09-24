#include <iostream>
#include <vector>

using namespace std;

class QuickUnionUF{
    vector <int> id;
public:
    QuickUnionUF(int N){
        for(int i = 0; i < N; i++)
            id.push_back(i);
    }
    int root(int i){
        while(i != id[i]) i = id[i];
        return i;
    }
    bool connected(int p, int q){
        return root(p) == root(q);
    }
    void Union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
};

int main(){

    return 0;
}
