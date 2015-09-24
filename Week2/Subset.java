public class Subset{
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue <String> rq = new RandomizedQueue <String>();
        String[] s = StdIn.readStrings();
        for(int i = 0; i < s.length; i++)
            rq.enqueue(s[i]);
        for(int i = 0; i < k; i++)
            System.out.println(rq.dequeue());
    }
}