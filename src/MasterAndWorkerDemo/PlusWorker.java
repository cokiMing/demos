package MasterAndWorkerDemo;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class PlusWorker extends Worker<Integer,Integer> {

    @Override
    public Integer handle(Integer input) {
        try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        return input * input * input;
    }
}
