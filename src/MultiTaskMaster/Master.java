package MultiTaskMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Master {

    private Map<String,Object> argsMap = new HashMap<>();

    private Map<String,Thread> threadMap = new HashMap<>();

    private Map<String,Object> resultMap = new ConcurrentHashMap<>();

    private Map<String,Exception> exceptionMap = new ConcurrentHashMap<>();

    private List<Worker> workerList = new ArrayList<>();

    public Map<String, Object> getArgsMap() {
        return argsMap;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public Map<String, Exception> getExceptionMap() {
        return exceptionMap;
    }

    public Master (Map<String,Object> argsMap){
        this.argsMap = argsMap;
    }

    //若一轮内的所有任务完成则清空任务池
    public boolean isComplete(){
        for(Map.Entry<String , Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        cleanThreads();
        cleanWorkerList();
        return true;
    }

    //清空任务池
    public void cleanThreads(){
        threadMap.clear();
    }

    public void cleanWorkerList(){
        workerList.clear();
    }

    //打印异常信息
    public void printExceptions(){
        for(Map.Entry<String,Exception> entry: exceptionMap.entrySet()){
            System.out.println("[Thread Exception]: "+ entry.getKey() + "---" + entry.getValue());
        }
    }

    //查看一轮内的任务是否有异常
    public boolean isException(){
        return !exceptionMap.isEmpty();
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
            Thread thread = new Thread(worker);
            threadMap.put(Integer.toString(i),thread);
        }
        for(Map.Entry<String,Thread> map : threadMap.entrySet()){
            map.getValue().start();
        }
    }
}


