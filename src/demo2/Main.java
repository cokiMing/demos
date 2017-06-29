package demo2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by wuyiming on 2017/6/22.
 */
public class Main {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args){
        System.out.println(System.currentTimeMillis());
        InfoCall infoCall1 = new InfoCall("ok1",1000);
        InfoCall infoCall2 = new InfoCall("ok2",2000);
        InfoCall infoCall3 = new InfoCall("ok3",3000);

        FutureTask<String> futureTask1 = new FutureTask<>(infoCall1);
        FutureTask<String> futureTask2 = new FutureTask<>(infoCall2);
        FutureTask<String> futureTask3 = new FutureTask<>(infoCall3);

        executorService.execute(futureTask1);
        executorService.execute(futureTask2);
        executorService.execute(futureTask3);
        try{
            if(futureTask1.get() == null || futureTask2.get() == null || futureTask3.get() == null){
                System.out.println(System.currentTimeMillis());
                throw new Exception("线程异常");
            }
            System.out.println(futureTask1.get()+":"+futureTask2.get()+":"+futureTask3.get());
            System.out.println(System.currentTimeMillis());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

class InfoCall implements Callable<String> {

    private String message;
    private long sleepTime;

    public InfoCall(String message, long sleepTime){
        this.message = message;
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(sleepTime);
        return message;
    }
}