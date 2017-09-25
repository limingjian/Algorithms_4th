import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ArrayST<Key, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;

	public ArrayST(int n) {
		keys = (Key[]) new Object[n];
		vals = (Value[]) new Object[n];
		N = 0;
	}

	public void put(Key key, Value val) {
		for (int i = 0; i < N; i++) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[N] = key;
		vals[N] = val;
		N++;
	}
	
	public Value get(Key key) {
		for (int i = 0; i < N; i++) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}

	public void delete(Key key) {
		for (int i = 0; i < N; i++) {
			if (keys[i].equals(key)) {
				keys[i] = keys[N - 1];
				vals[i] = vals[N - 1];
				keys[N - 1] = null;
				vals[N - 1] = null;
				N--;
				return;
			}
		}
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return N;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < N; i++) {
			queue.enqueue(keys[i]);
		}
		return queue;
	}

	public static void main(String[] args) {
		ArrayST<String, Integer> st = new ArrayST<String, Integer>(20);
		int i=0;
		while(!StdIn.isEmpty()) {
			String key=StdIn.readString();
			st.put(key, i);
			i++;
		}
		for (String s:st.keys()) {
			StdOut.println(s+" "+st.get(s));
		}
	}
}
