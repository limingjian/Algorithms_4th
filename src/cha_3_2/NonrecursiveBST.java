package cha_3_2;

import java.util.Stack;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
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
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.val;
		}
		return null;
	}

	public void put(Key key, Value val) {
		Node z = new Node(key, val, 1);
		if (root == null) {
			root = z;
			return;
		}

		Node parent = null, x = root;
		while (x != null) {
			parent = x;
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else {
				x.val = val;
				return;
			}
		}
		int cmp = key.compareTo(parent.key);
		if (cmp < 0)
			parent.left = z;
		else
			parent.right = z;
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
		Stack<Node> stack = new Stack<Node>();
		Queue<Key> queue = new Queue<Key>();
		Node x = root;
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				stack.push(x);
				x = x.left;
			} else {
				x = stack.pop();
				queue.enqueue(x.key);
				x = x.right;
			}
		}
		return queue;
	}

	public static void main(String[] args) {
		NonrecursiveBST<String, Integer> st = new NonrecursiveBST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
	}
}
