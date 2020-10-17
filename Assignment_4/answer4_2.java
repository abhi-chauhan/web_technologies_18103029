class answer4_2 {

    public static void main(String[] args){
        int i=-1;
        byte b=(byte) i;//b=-1
        char c=(char) b;//c=65535
        int n=(int) c;//n=65535
        System.out.println(n);
    }

}
// output= 65535
/*when we cast a negative to char, the result is the same as subtracting that number from 65536, resulting in 65535.
When we convert byte to int it converts 65535 to int which is 65535 only.*/