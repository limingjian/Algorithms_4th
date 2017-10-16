package cha_3_2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST_plus_height_field<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		private int height;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.height= 0;  //the height of this sub-tree
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.N;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		if (x.left==null || x.right==null) {
			x.height+=1;
		}
		return x;
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return floor(x.left, key);
		if (cmp == 0)
			return x;
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp > 0)
			return floor(x.right, key);
		if (cmp == 0)
			return x;
		Node t = floor(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) {
		if (x == null)
			return null;
		int t = size(x.left);
		if (t > k)
			return select(x.left, k);
		if (t < k)
			return select(x.right, k - t - 1);
		else
			return x;
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key);
		if (cmp > 0)
			return 1 + size(x.left) + rank(x.right, key);
		else
			return size(x.left);
	}

	public void deletemin() {
		root = deletemin(root);
	}

	private Node deletemin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deletemin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deletemax() {
		root = deletemax(root);
	}

	private Node deletemax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deletemax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deletemin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	public int height() {
		return root.height + 1;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);
	}

	public static void main(String[] args) {
		BST_plus_height_field<String, Integer> st = new BST_plus_height_field<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
		StdOut.println("The height of the BST is: " + st.height());
	}
}
