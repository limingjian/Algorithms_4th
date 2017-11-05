package Others;

public class test {
	public static void main(String[] args) {
		String str="aaa\"bbb\"ccc\"ddd\"eee";
		System.out.println(str);
		str=str.replaceAll("\"(.*)\"", "@");
		System.out.println(str);
	}
}
