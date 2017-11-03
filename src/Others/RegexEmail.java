package Others;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.princeton.cs.algs4.StdIn;

public class RegexEmail {
	public static boolean checkEmail(String email) {
		boolean flag=false;
		String pat="^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+$";
		Pattern regex=Pattern.compile(pat);
		Matcher matcher=regex.matcher(email);
		flag=matcher.matches();
		return flag;
	}

	public static void main(String[] args){
		while (!StdIn.isEmpty()) {
			String email=StdIn.readString();
			System.out.println(checkEmail(email));
		}
	}
}
