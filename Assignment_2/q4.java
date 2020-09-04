public class q4 {
    public static void main(String[] args) {
        int summation=0, i=1;
        for(; i<Integer.MAX_VALUE; i++) {
            if(summation >= Integer.MAX_VALUE-i) break;
            summation+=i;
            if((int)Math.sqrt(summation)==i)
                System.out.println(i);
        }
        System.out.println("The answer for the given question is: "+i);
    }
}
