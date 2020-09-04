import java.util.Scanner;

public class q6 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter any positive number for hailstone sequence: ");
        long n = input.nextLong();
        while (n<= 0) {
            System.out.println("Invalid Range. Please enter again: ");
            n=input.nextLong();
        }
        input.close();
        System.out.println("Hailstone Sequence : ");
        while (n != 1) {
            System.out.print(n + " ");
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n *= 3;
                n++;
            }
        }

        System.out.println(n);


    }

}