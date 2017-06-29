package MasterAndWorkerDemo;

import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Main {

    public static void main(String args[]){
        method1(20);
        method2(20);
    }

    public static void method1(int n){
        long start = System.currentTimeMillis();
        Worker worker = new PlusWorker();
        Master master = new Master(worker,10);

        for (int i =1 ; i < n ; i++){
            master.submit(i);
        }

        master.execute();
        while (!master.isComplete());
        Map<String, Object> resultMap = master.getResultMap();
        for (Map.Entry<String,Object> entry :  resultMap.entrySet()){
            System.out.println(entry.getValue());
        }
        System.out.println("master-worker耗时: "+ (System.currentTimeMillis() - start));
    }

    public static void method2(int n){
        long start = System.currentTimeMillis();
        for(int i =1 ; i <n ; i++){
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(i * i * i);
        }
        System.out.println("单线程耗时: "+ (System.currentTimeMillis() - start));
    }
}
