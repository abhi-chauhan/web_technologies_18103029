import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;


public class q2 {
	public static void main(String[] args) {
		Scanner User_input = new Scanner(System.in);
		System.out.print("Please provide a paragraph: ");
		String content = User_input.nextLine();
		System.out.print("Please provide the string which you want to change : ");
		String[] bad_words = User_input.nextLine().split(" ");
		User_input.close();
		for (String word : bad_words)
		{
			Pattern pattern = Pattern.compile("\\b" + word + "\\b");
			Matcher checker = pattern.matcher(content);
			if (checker.find())
			{
				StringBuilder new_Str = new StringBuilder();
				new_Str.append(word.charAt(0));
				for (int i = 1; i < word.length(); i++)
				{
					new_Str.append('*');
				}
				content = checker.replaceAll(new_Str.toString());
			}
		}
		System.out.println("Updated Paragraph: " + content);
	}
}