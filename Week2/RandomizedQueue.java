import java.util.Iterator;

public class RandomizedQueue <Item> implements Iterable <Item>{
    private Node first, last;
    private int n = 0;
    private class Node{
        Item item;
        Node next;
    }
    public RandomizedQueue(){
        first = null;
        last = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return n;
    }
    public void enqueue(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }
    public Item dequeue(){
        if(n == 0) throw new java.util.NoSuchElementException();
        Item item;
        int m = StdRandom.uniform(n);
        System.out.println(m);
        if(m == 0){
            item = first.item;
            first = first.next;
        }
        else{
            Node it = first;
            for(int i = 0; i < m - 1; i++)
                it = it.next;
            item = it.next.item;
            it.next = it.next.next;
        }
        if(isEmpty()) last = null;
        n--;
        return item;
    }
    public Item sample(){
        if(n == 0) throw new java.util.NoSuchElementException();
        int m = StdRandom.uniform(n);
        System.out.println(m);
        Iterator <Item> it = iterator();
        Item item = first.item;
        for(int i = 0; i <= m; i++)
            item = it.next();
        return item;
    }
    public Iterator <Item> iterator(){ return new ListIterator();}
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){ return current != null;}
        public void remove(){ throw new java.lang.UnsupportedOperationException();}      
        public Item next(){
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args){
        RandomizedQueue <String> rq = new RandomizedQueue <String>();
        rq.enqueue("20");
        rq.enqueue("10");
        rq.enqueue("30");
        rq.enqueue("5");
        rq.enqueue("6");
        rq.enqueue("7");
        Iterator <String> it = rq.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        rq.dequeue();
        rq.dequeue();
        System.out.println(rq.sample());
        System.out.println();
        it = rq.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}