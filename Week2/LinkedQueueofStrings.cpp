#include <iostream>

using namespace std;

template <class T>

class LinkedQueue{
    struct Node{
        T item;
        Node *next;
    };
    Node *first, *last;
public:
    LinkedQueue(){
        first = NULL;
    }
    bool isEmpty(){
        return first == NULL;
    }
    void enqueue(T item){
        Node *oldlast = last;
        last = new Node;
        last->item = item;
        last->next = NULL;
        if(isEmpty()) first = last;
        else oldlast->next = last;
    }
    T dequeue(){
        T item = first->item;
        first = first->next;
        if(isEmpty()) last = NULL;
        return item;
    }
};

int main(){
    LinkedQueue<string> cola;
    cola.enqueue("uno");
    cola.enqueue("dos");
    cola.enqueue("tres");
    cola.enqueue("cuatro");
    cout << cola.dequeue() << endl;
    cout << cola.dequeue();
    return 0;
}
