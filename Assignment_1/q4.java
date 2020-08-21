import java.util.Scanner;
import java.util.HashMap;


public class q4 {
	public static void main(String[] args) {
		Scanner user_Input = new Scanner(System.in);
		System.out.print("Enter first string: ");
		String string_1 = user_Input.nextLine();
		System.out.print("Enter second string: ");
		String string_2 = user_Input.nextLine();
		user_Input.close();

		HashMap<Character, Integer> map_1 = new HashMap<Character, Integer>();
		for (Character c : string_1.toCharArray()) {
			map_1.put(c, 1 + map_1.getOrDefault(c, 0));
		}
		HashMap<Character, Integer> map_2 = new HashMap<Character, Integer>();
		for (Character c : string_2.toCharArray()) {
			map_2.put(c, 1 + map_2.getOrDefault(c, 0));
		}
		if (map_1.equals(map_2))
		{
			System.out.print("They are anagrams !");
		}
		else
		{
			System.out.print("They are not anagrams !");
		}

	}
}
