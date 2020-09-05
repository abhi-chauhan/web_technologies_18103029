import java.util.*;
//takes an average of 3 milliseconds
public class q5_2 {

    public static void main(String[] args) {

        Set<Integer> set1=new HashSet<Integer>();
        Set<Integer> set2=new HashSet<Integer>();
        List<Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10);
        Set<Integer> universe=new HashSet<Integer>(list);


        Scanner userInput= new Scanner(System.in);
        System.out.println("Please enter number of elements in first set:");
        int s1=userInput.nextInt();
        System.out.println("Please enter number of elements in second set:");
        int s2=userInput.nextInt();
        System.out.println("Now enter elements of first set:");
        for(int i=0; i<s1; i++) {
            int a=userInput.nextInt();
            while(a<0 || a>10) {
                System.out.println("Please enter value between 0 and 10 both inlcusive: ");
                a=userInput.nextInt();
            }
            set1.add(a);
        }
        System.out.println("Now enter elements of second set:");
        for(int i=0; i<s2; i++) {
            int a=userInput.nextInt();
            while(a<0 || a>10) {
                System.out.println("Please enter value between 0 and 10 both inlcusive: ");
                a=userInput.nextInt();
            }
            set2.add(a);
        }
        long start = System.currentTimeMillis();
        Set<Integer> union=new HashSet<Integer>(set1);
        union.addAll(set2);
        System.out.println("Union of the two sets are: "+union);
        Set<Integer> intersection= new HashSet<Integer>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection of the two sets are: "+intersection);
        Set<Integer> complementSet1= new HashSet<Integer>(universe);
        complementSet1.removeAll(set1);
        System.out.println("Complement of set 1 is: "+complementSet1);
        Set<Integer> complementSet2= new HashSet<Integer>(universe);
        complementSet2.removeAll(set2);
        System.out.println("Complement of set 2 is: "+complementSet2);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" milliseconds");
    }
}