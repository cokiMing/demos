package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class NameWorker extends Worker {
    @Override
    public void handler() throws Exception{
        Object result1 = argsMap.get("name");
        Thread.sleep(100);
        //TODO 业务代码
        resultMap.put("result1",result1);
    }
}
