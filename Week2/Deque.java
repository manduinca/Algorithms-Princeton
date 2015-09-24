import java.util.Iterator;

public class Deque <Item> implements Iterable <Item>{
    private Node first, last;
    private int n;
    private class Node{
        Item item;
        Node previous, next;
    }
    public Deque(){
        Node first = null, last = null;
    }
    public boolean isEmpty(){
        return first == null || last == null;
    }
    public int size(){
        return n;
    }
    public void addFirst(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        if(isEmpty()){
            first.next = null;
            last = first;
        }
        else{
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        n++;
    }
    public void addLast(Item item){
        if(item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            last.previous = null;
            first = last;
        }
        else{
            oldlast.next = last;
            last.previous = oldlast;
        }
        n++;
    }
    public Item removeFirst(){
        if(n == 0) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
	else first.previous = null;
        n--;
        return item;
    }
    public Item removeLast(){
        if(n == 0) throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if(isEmpty()) first = null;
	else last.next = null;
        n--;
        return item; 
    }
    public Iterator <Item> iterator(){ return new ListIterator();}
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){ return current != null;}
        public void remove(){throw new java.lang.UnsupportedOperationException();}      
        public Item next(){
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    public static void main(String[] args){
        Deque <String> d = new Deque <String>();
        d.addFirst("20");
        d.addFirst("10");
        d.addFirst("30");
        d.addLast("5");
        d.addLast("6");
        d.addLast("7");
        Iterator <String> it = d.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
	d.removeFirst();
	d.addFirst("1");
        it = d.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}