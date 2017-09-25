import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SequentialSearchST<Key,Value> {
	private Node first;
	private class Node{
		Key key;
		Value val;
		Node next;
		public Node(Key key,Value val,Node next) {
			this.key=key;
			this.val=val;
			this.next=next;
		}
	}
	public SequentialSearchST() {};
	public Value get(Key key) {
		for (Node x=first;x!=null;x=x.next) {
			if (x.key.equals(key)) return x.val;
		}
		return null;
	}
	public void put(Key key,Value val) {
		for (Node x=first;x!=null;x=x.next) {
			if (x.key.equals(key)) x.val=val;
		}
		first=new Node(key,val,first);
	}
	public int size() {
		int i=0;
		for (Node x=first;x!=null;x=x.next) {
			i++;
		}
		return i;
	}
	public void delete(Key key) {
		if (first.key.equals(key)) first=first.next;
		for (Node x=first;x!=null;x=x.next) {
			if(x.next.key.equals(key)) {
				x.next=x.next.next;
			}
		}
	}
    
	public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
    
	public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
