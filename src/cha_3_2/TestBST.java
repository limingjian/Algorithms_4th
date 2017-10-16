package cha_3_2;

import edu.princeton.cs.algs4.StdOut;

public class TestBST {
	public static void main(String[] args) {
		String test = "S E A R C H E X A M P L E";
		String[] keys = test.split(" ");
		int n = keys.length;
		BST<String, Integer> st = new BST<String, Integer>();
		for (int i = 0; i < n; i++)
			st.put(keys[i], i);
		// test of min()
		StdOut.println("min: " + st.min());
		// test of max()
		StdOut.println("max: " + st.max());
		// test of floor()
		StdOut.println("floor(\"F\"): " + st.floor("F"));
		// test of ceiling()
		StdOut.println("ceiling(\"F\"): " + st.ceiling("F"));
		// test of select()
		StdOut.println("select(1): " + st.select(1));
		// test of rank()
		StdOut.println("rank(\"P\"): " + st.rank("P"));
		// test of keys()
		StdOut.println("The original trees:");
		for (String s : st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
		// test of deletemin()
		StdOut.println("deletemin():");
		st.deletemin();
		for (String s : st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
		// test of deletemax()
		StdOut.println("deletemax():");
		st.deletemax();
		for (String s : st.keys()) {
			StdOut.println(s + " " + st.get(s));
		}
	}
}
