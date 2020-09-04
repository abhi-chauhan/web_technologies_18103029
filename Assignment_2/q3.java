import java.util.Scanner;
public class q3
{
    private static int compare(String a, String b) {

        for(int i=0; i<a.length() && i<b.length();i++ ){
            if (a.charAt(i) < b.charAt(i)) {
                return -1;
            } else if (a.charAt(i) > b.charAt(i)) {
                return 1;
            }
        }
        if (a.length() == b.length()) {
            return 0;
        }
        if (a.length() > b.length()) {
            return 1;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int count;
        String t;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of strings you want: ");
        count = scanner.nextInt();
        String str[] = new String[count];
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter the Strings one by one: ");
        for(int i = 0; i < count; i++)
        {
            str[i] = scanner2.nextLine();
        }
        scanner.close();
        scanner2.close();
        for (int i = 0; i < count; i++)
        {
            for (int j = i + 1; j < count; j++) {
                if (compare(str[i].toLowerCase(),str[j].toLowerCase())>0)
                {
                    t = str[i];
                    str[i] = str[j];
                    str[j] = t;
                }
            }
        }
        System.out.print("Strings in Sorted Order: ");
        for (int i = 0; i <= count - 1; i++)
        {  if(i==(count-1)) System.out.print(str[i]);
        else System.out.print(str[i] + ", ");
        }
    }
}