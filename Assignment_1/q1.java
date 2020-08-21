import java.util.Scanner;
import java.util.HashMap;


public class q1 {
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please provide a string: ");
		String str = userInput.nextLine();
		System.out.print("Please provide a subString: ");
		String subStr = userInput.nextLine();
		userInput.close();
		if (str.length() < subStr.length())
		{
			System.out.println(0);
		} else {
			HashMap<Character, Integer> subStrMap = new HashMap<Character, Integer>();
			for (Character c : subStr.toCharArray())
			{
				subStrMap.put(c, 1 + subStrMap.getOrDefault(c, 0));
			}
			HashMap<Character, Integer> mapTemp = new HashMap<Character, Integer>();
			int first = 0;
			int counter = 0;
			for (int i = 0; i < str.length(); i++)
			{
				if (i >= subStr.length())
				{
					mapTemp.put(str.charAt(first), mapTemp.get(str.charAt(first)) - 1);
					if (mapTemp.get(str.charAt(first)) == 0)
					{
						mapTemp.remove(str.charAt(first));
					}
					first++;
				}
				mapTemp.put(str.charAt(i), 1 + mapTemp.getOrDefault(str.charAt(i), 0));
				if (mapTemp.equals(subStrMap))
				{
					counter++;
				}
			}
			System.out.println('\n' + "Frequency of substring occuring in the given string is: " + counter);

		}

	}
}
