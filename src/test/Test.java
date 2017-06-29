package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by feng on 2017/4/25.
 */
public class Test {


    public static void main(String args[]) throws Exception{
        String specifiedTime1 = "2017-05-09 17:59:20";
        String specifiedTime2 = "2017-05-09 17:59:25";
        startAsync(specifiedTime1);
        startAsync(specifiedTime2);

        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            System.out.println(thread.getName()+"："+thread.getId());
            if(thread.getId() == 11){
                thread.suspend();
            }
        }
    }

    public static void startAsync(String specifiedTime){

        Runnable run = new Runnable(){
            Test test = new Test();
            @Override
            public void run(){
                try {
                    test.test(specifiedTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Executors.newCachedThreadPool().execute(run);

    }

    public void test(String specifiedTime)throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse(specifiedTime);
        TimerManager timerManager = new TimerManager();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run(){
                Date time = Calendar.getInstance().getTime();
                String dateString = df.format(time);
                System.out.println("time is "+dateString);
                timerManager.closeTimer();
            }
        };

        timerManager.sendMsgInTime(timerTask,time);
    }

    class TimerManager{

        Timer timer = new Timer();

        public  void sendMsgInTime(TimerTask timerTask,Date specifiedTime)throws Exception{
            Date curTime = Calendar.getInstance().getTime();

            if(curTime.after(specifiedTime)){
                timer.schedule(timerTask,0);
            }else{
                timer.schedule(timerTask,specifiedTime);
            }
        }

        public void closeTimer(){
            timer.cancel();
        }

    }


}
