package MultiTaskMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Master类代表一个纵向的任务，
 * 如果需要控制更紧密的任务耗时，
 * 可以使用多个Master,但并不建议这样做。
 *
 * @author wuyiming
 * @since 1.0
 *
 * Created by wuyiming on 2017/6/29.
 */
public class Master {

    //参数集——用于存储入口的参数
    private Map<String,Object> argsMap = new HashMap<>();

    //线程集——master的线程池，用于调配worker任务
    private Map<String,Thread> threadMap = new HashMap<>();

    //结果集——用于存储worker执行结果
    private Map<String,Object> resultMap = new ConcurrentHashMap<>();

    //异常集——用于存储worker执行过程中产生的异常
    private volatile Map<String,Exception> exceptionMap = new ConcurrentHashMap<>();

    //失败集——用于存储worker执行过程中产生的业务失败集合
    private volatile Map<String,String> failMap = new ConcurrentHashMap<>();

    //任务集——用于存储worker
    private List<Worker> workerList = new ArrayList<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public Master (Map<String,Object> argsMap){
        this.argsMap = argsMap;
    }

    //清空线程池
    public void cleanThreads(){
        threadMap.clear();
    }

    //查看所有任务是否已经完毕
    public boolean isComplete(){
        for(Map.Entry<String , Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //清空任务池
    public void cleanWorkerList(){
        workerList.clear();
    }

    //打印异常信息
    public void printExceptions(){
        for(Map.Entry<String,Exception> entry: exceptionMap.entrySet()){
            System.out.println("[Thread Exception]: "+ entry.getKey() + "---" + entry.getValue());
        }
    }

    //获取业务失败信息
    public String getFailMessage(){
        for(Map.Entry<String,String> entry: failMap.entrySet()){
            return entry.getValue();
        }
        return null;
    }

    //查看一轮内的任务是否有异常
    public boolean isException(){
        return !exceptionMap.isEmpty();
    }

    //查看一轮内的任务是否有业务失败
    public boolean isFail(){
        return !failMap.isEmpty();
    }

    //设置单轮任务涉及的Worker类
    public void setWorker(Worker worker){
        workerList.add(worker);
    }

    //设置单轮任务涉及的所有Worker类
    public void setWorker(List<Worker> workers){
        workers.addAll(workers);
    }

    //开启所有任务
    public void execute(){
        for (int i = 0; i < workerList.size(); i++){
            Worker worker = workerList.get(i);
            worker.setArgsMap(argsMap);
            worker.setResultMap(resultMap);
            worker.setExceptionMap(exceptionMap);
            worker.setFailMap(failMap);
            Thread thread = new Thread(worker);
            threadMap.put(Integer.toString(i),thread);
        }
        for(Map.Entry<String,Thread> map : threadMap.entrySet()){
            map.getValue().start();
        }
    }

    //中断所有任务
    public void interrupt(){
        for(Map.Entry<String,Thread> map : threadMap.entrySet()){
            map.getValue().interrupt();
        }
    }
}


