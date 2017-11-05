package Others;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class RegexEmail {
	public static boolean checkEmail(String email) {
		String pat="^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+$";
		Pattern regex=Pattern.compile(pat);
		Matcher matcher=regex.matcher(email);
		return matcher.matches();
	}

	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while (in.hasNext()) {
			String email=in.nextLine();
			System.out.println(checkEmail(email));
		}
	}
}
