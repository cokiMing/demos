package MultiTaskMaster;

import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public abstract class Worker implements Runnable{

    protected Map<String,Object> argsMap;

    protected Map<String,Object> resultMap;

    protected Map<String,Exception> exceptionMap;

    public void setArgsMap(Map<String, Object> argsMap) {
        this.argsMap = argsMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setExceptionMap(Map<String, Exception> exceptionMap) {
        this.exceptionMap = exceptionMap;
    }

    public Worker(){}

    public Worker(Master master){
        this.argsMap = master.getArgsMap();
        this.resultMap = master.getResultMap();
        this.exceptionMap = master.getExceptionMap();
    }

    /**
     * 实现该方法时，可以将每个线程的运行结果放到结果集中，
     * 结果集作为后续线程的参数，可以调用
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
