package networking;

class MyThread implements Runnable{
    Thread t;
    int threadNumber;
    public MyThread(int id, String threadName){
        threadNumber = id;
        t = new Thread( this, threadName);
        t.start();
    }


    @Override
    public void run() {
        for(int i=0; i<10; i++){
            System.out.println(threadNumber + " is running - " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
public class MainThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread ob = new MyThread(1, "thread-1");
        MyThread ob2 = new MyThread(2, "thread-2");

        // wait until threads are alive
        ob.t.join(); // it maybe throws InterruptedException
        ob2.t.join();

        System.out.println("Exit from MainThread..");
    }
}
