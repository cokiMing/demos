package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class AddressWorker extends Worker {
    @Override
    public void handler() throws Exception{
        Object result3 = argsMap.get("address");
        //TODO 业务代码
        Thread.sleep(200);
        resultMap.put("result3",result3);
    }
}
