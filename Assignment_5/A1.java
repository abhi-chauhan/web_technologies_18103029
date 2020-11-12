public class A1 extends Thread {

    public static void main(String[] args) {
        A1 object = new A1();
        object.start();

    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
            if (i % 10 == 0) {
                System.out.println("Counted Ten Numbers");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                System.out.println("Unable to put thread to sleep");
            }
        }

    }
}