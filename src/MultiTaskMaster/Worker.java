package MultiTaskMaster;

import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public abstract class Worker implements Runnable{

    protected Map<String,Object> argsMap;

    protected Map<String,Object> resultMap;

    protected volatile Map<String,Exception> exceptionMap;

    protected volatile Map<String,String> failMap;

    public void setArgsMap(Map<String, Object> argsMap) {
        this.argsMap = argsMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setExceptionMap(Map<String, Exception> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }

    public void setFailMap(Map<String, String> failMap) {
        this.failMap = failMap;
    }

    public Worker(){}

    /**
     * 实现该方法时，将每个线程的运行结果放到结果集中，
     * 用作后续线程的参数调用，
     * 业务失败时将失败信息放到失败结果集中，
     * 并结束整个方法
     * @throws Exception
     */
    public abstract void handler() throws Exception;

    public void execute(){
        try{
            long millis = System.currentTimeMillis();
            handler();
            System.out.println(this.getClass().getName() + " 耗时: "+ (System.currentTimeMillis() - millis));
        }catch (Exception e){
            String className = this.getClass().getName();
            exceptionMap.put(className,e);
        }
    }

    @Override
    public void run() {
        execute();
    }
}
