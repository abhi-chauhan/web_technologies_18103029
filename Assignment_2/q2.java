import java.util.Scanner;

public class q2 {
    static void countingSort(int numbers[], int a){
        int reference[]= new int [21];
        for(int i=0; i<21; i++) reference[i]=0;
        for(int i=0; i<a; i++) {
            reference[numbers[i]]++;
        }
        int index=0;
        for(int i=0; i<21; i++) {
            for(int j=0; j<reference[i]; j++) {
                numbers[index]=i;
                index++;
            }
        }
        for(int i=0; i<a; i++) {
            System.out.print(numbers[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner userInput= new Scanner(System.in);
        System.out.println("Enter number of integers you want to enter:");
        int a=userInput.nextInt();
        int[] numbers= new int[a];
        System.out.println("Enter the integers within the range of 0 to 20 (inclusive)");
        for(int i=0; i<a; i++) {
            numbers[i]=userInput.nextInt();
            while(numbers[i]>20 || numbers[i]<0) {
                System.out.println("Invalid selection of number. Only the numbers between 0 and 20 are acceptable, so kindly enter another.");
                numbers[i] = userInput.nextInt();
            }
        }

    System.out.print("The numbers after implementing counting sort are:");
        countingSort(numbers,a);
    }
}