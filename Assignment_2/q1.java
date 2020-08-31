import java.util.Scanner;

public class q1 {
    private static String compare(String a, String b) {

        for(int i=0; i<a.length() && i<b.length();i++ ){
            if (a.charAt(i) < b.charAt(i)) {
                return "First string will come first";
            } else if (a.charAt(i) > b.charAt(i)) {
                return "Second string will come first";
            }

        }
        if (a.length() == b.length()) {
            return "Both strings are equal";
        }
        if (a.length() > b.length()) {
            return "Second string will come first";
        }
        return "First string will come first";
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter First String: ");
        String string1 = userInput.nextLine();
        System.out.print("Enter Second String: ");
        String string2 = userInput.nextLine();
        System.out.println("Output: " + compare(string1.toLowerCase(), string2.toLowerCase()));
        userInput.close();
    }
}