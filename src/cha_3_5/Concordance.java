package cha_3_5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Concordance {
	public static void main(String[] args) {
		int CONTEXT = 5;
		In in = new In(args[0]);
		String[] words = in.readAllStrings();
		ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!st.contains(s)) {
				st.put(s, new SET<Integer>());
				st.get(s).add(i);
			}
		}
		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			SET<Integer> set = st.get(query);
			if (set == null)
				set = new SET<Integer>();
			for (int k : set) {
				for (int i = Math.max(0, k - CONTEXT + 1); i < k; i++) {
					StdOut.print(words[i] + " ");
				}
				StdOut.print(words[k] + " ");
				for (int i = k + 1; i < Math.min(k + CONTEXT, words.length); i++) {
					StdOut.print(words[i] + " ");
				}
				StdOut.println();
			}
			StdOut.println();
		}
	}
}
