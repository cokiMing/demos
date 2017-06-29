package MasterAndWorkerDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Master {

    protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();

    protected Map<String,Thread> threadMap = new HashMap<>();

    protected Map<String,Object> resultMap = new ConcurrentHashMap<>();

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public Master(Worker worker, int countWoker){
        worker.setQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i=0; i < countWoker; i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    /**
     * 查看所有子任务是否已经完成
     * @return
     */
    public boolean isComplete(){
        for(Map.Entry<String , Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    /**
     * 添加一个任务
     * @param job
     */
    public void submit( Object job){
        workQueue.add(job);
    }

    /**
     * 获取结果集
     * @return
     */
    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    /**
     * 开启所有子任务
     */
    public void execute(){
        for (Map.Entry<String , Thread> entry:threadMap.entrySet()){
            entry.getValue().start();
        }
    }
}
