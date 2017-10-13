package test;

import java.util.concurrent.*;

/**
 * Created by feng on 2017/4/25.
 */
public class Test extends Thread{

    private int id;

    public static void main(String args[]) throws Exception{
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
//                new LinkedBlockingQueue<>()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                new MyReject()
        );

        Test test1 = new Test(1);
        Test test2 = new Test(2);
        Test test3 = new Test(3);
        Test test4 = new Test(4);
        Test test5 = new Test(5);
        Test test6 = new Test(6);

        threadPoolExecutor.execute(test1);
        threadPoolExecutor.execute(test2);
        threadPoolExecutor.execute(test3);
        threadPoolExecutor.execute(test4);
        threadPoolExecutor.execute(test5);
        threadPoolExecutor.execute(test6);

        threadPoolExecutor.shutdown();

    }

    public Test(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(id + " run...");
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                '}';
    }
}
