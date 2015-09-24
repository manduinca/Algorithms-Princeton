#include <iostream>

using namespace std;

template <class T>

class LinkedStack{
    struct Node{
        T item;
        Node *next;
    };
    Node *first;
public:
    LinkedStack(){
        first = new Node;
        first->next = first;
    }
    bool isEmpty(){
        return first->next == first;
    }
    void push(T item){
        Node *oldfirst = first;
        first = new Node;
        first->item = item;
        first->next = oldfirst;
    }
    string pop(){
        T item = first->item;
        first = first->next;
        return item;
    }
};

int main(){
    LinkedStack<string> pila;
    pila.push("uno");
    pila.push("dos");
    pila.push("tres");
    pila.push("cuatro");
    cout << pila.pop() << endl;
    cout << pila.pop();
    return 0;
}
