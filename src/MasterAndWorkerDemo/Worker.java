package MasterAndWorkerDemo;

import java.util.Map;
import java.util.Queue;

/**
 * Created by wuyiming on 2017/6/29.
 */
public abstract class Worker<T,I> implements Runnable{

    protected Queue<I> queue;

    protected Map<String,Object> resultMap;

    public void setQueue(Queue<I> queue) {
        this.queue = queue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public abstract T handle(I input);

    @Override
    public void run() {
        while (true){
            I input = queue.poll();
            if(input == null){
                break;
            }

            T re = handle(input);
            resultMap.put(Integer.toString(input.hashCode()),re);
        }
    }
}
