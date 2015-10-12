import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int first = 0;       // index of first element of queue
    private int last  = 0;       // index of next available slot

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }
 
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int m = StdRandom.uniform(N);
        Item temp = q[first];
        q[first] = q[(first + m) % q.length];
        q[(first + m) % q.length] = temp;
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int m = StdRandom.uniform(N);
        return q[(first + m) % q.length];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] a = (Item[]) new Object[N];
        public ArrayIterator() {
            int k = 0;
            for(int j = 0; j < q.length; j++)
                if(q[j] != null) a[k++] = q[j];
            StdRandom.shuffle(a);
        }
        public boolean hasNext() { return i < N; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = a[i];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue <String> q = new RandomizedQueue <String> ();
        /*for(int i = 0; i < 100; i++)
            q.enqueue(i);
        Iterator <Integer> it = q.iterator();
        Iterator <Integer> it2 = q.iterator();
        while(it.hasNext()) {
            while(it2.hasNext()) {
                System.out.println(it.next() + " " + it2.next());
            }
        }*/
         //System.out.println(q.size());        
         q.enqueue("451");
         //System.out.println(q.dequeue());        
        // System.out.println(q.isEmpty());        
         //System.out.println(q.isEmpty());        
         //System.out.println(q.isEmpty());        
         //System.out.println(q.size());        
         q.enqueue("423");
        // q.enqueue("466");
         System.out.println(q.dequeue());        
         System.out.println(q.sample());        
        /*
        q.enqueue(0);
        System.out.println(q.sample());
        System.out.println(q.sample());
        System.out.println(q.sample());
        q.enqueue(2);
        System.out.println(q.sample());
        System.out.println(q.sample());
        q.enqueue(1);
        System.out.println(q.sample());
        System.out.println(q.sample());
        System.out.println(q.sample());*/
    }
}
