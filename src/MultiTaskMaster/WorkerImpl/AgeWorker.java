package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class AgeWorker extends Worker{
    @Override
    public void handler() throws Exception{
        Object result2 = argsMap.get("age");
        //TODO 业务代码
        Thread.sleep(100);
        resultMap.put("result2",result2);
    }
}
