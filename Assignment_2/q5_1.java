import java.util.Scanner;
//takes on average 6 milliseconds to compute
public class q5_1 {

    public static void intersection(int []a1, int[] a2) {
        System.out.println("Intersection of the two arrays : ");
        int check=0;
        for(int i = 0; i<a1.length; i++ ) {
            for(int j = 0; j<a2.length; j++) {
                if(a1[i]==a2[j]) {
                    System.out.print(a2[j]+" ");
                    check=1;
                }
            }
        }
        if(check==0){System.out.print("No intersection possible");}
    }

    public static void union(int []a1, int[] a2) {
        int i = 0, j = 0;
        System.out.println("Union is: ");
        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j])
                System.out.print(a1[i++] + " ");
            else if (a2[j] < a1[i])
                System.out.print(a2[j++] + " ");
            else {
                System.out.print(a2[j++] + " ");
                i++;
            }
        }
        while (i < a1.length)
            System.out.print(a1[i++] + " ");
        while (j < a2.length)
            System.out.print(a2[j++] + " ");
        System.out.println(" ");
    }

    public static void complement(int []a1, int []universe) {
        int counter=0;
        int []result=new int[11];
        int i=0, j=0, flag=1;
        while(counter<11 && i<a1.length) {
            if(a1[i]==universe[counter]) {
                i++;
                counter++;
            }
            else {
                result[j++]=universe[counter++];
                flag=0;
            }
        }
        while(counter<11) {
            result[j++] = universe[counter++];
            flag = 0;
        }
        System.out.println("\nComplement is:");
        if(flag==1)
            System.out.print("NULL");
        else
            for(i=0; i<j; i++)
                System.out.print(" " + result[i]);
    }

    public static int[] setting(int[] a1) {
        int count=a1.length;
        int ref[]= new int [11];
        for(int i=0; i<11; i++)
            ref[i]=0;
        for(int i=0; i<count; i++) {
            ref[a1[i]]++;
        }
        int length=0;
        for(int i=0; i<11; i++) {
            if(ref[i]!=0) {
                a1[length]=i;
                length++;
            }
        }
        int []sorted= new int [length];
        for(int i=0; i<length; i++)
            sorted[i]=a1[i];
        return sorted;
    }

    public static void main(String[] args) {

        Scanner userInput = new Scanner (System.in);
        System.out.println("Enter number of elements in first set:");
        int n1=userInput.nextInt();
        System.out.println("Enter number of elements in second set:");
        int n2=userInput.nextInt(), count=0;
        int universe[]={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int set1[]= new int[n1];
        int set2[]= new int[n2];
        System.out.println("Enter the elements of first set:");
        for(int i=0; i<n1; i++) {
            set1[i] = userInput.nextInt();
            while(set1[i]<0 || set1[i]>10) {
                System.out.println("Please enter numbers only between 0 and 10 , both inclusive: ");
                set1[i]=userInput.nextInt();
            }
        }
        System.out.println("Enter the elements of second set:");
        for(int i=0; i<n2; i++) {
            set2[i] = userInput.nextInt();
            while(set2[i]<0 || set2[i]>10) {
                System.out.println("Please enter numbers only between 0 and 10 , both inclusive: ");
                set2[i]=userInput.nextInt();
            }
        }
        long start = System.currentTimeMillis();
        set1= setting(set1);
        set2= setting(set2);
        union(set1, set2);
        intersection(set1, set2);
        complement(set1, universe);
        System.out.print(" of first set");
        complement(set2, universe);
        System.out.println(" of second set");
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" milliseconds");
    }
}