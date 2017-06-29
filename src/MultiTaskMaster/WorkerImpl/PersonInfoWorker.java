package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class PersonInfoWorker extends Worker{
    @Override
    public void handler() throws Exception {
        Thread.sleep(100);
        String name = (String)resultMap.get("result1");
        String address = (String)resultMap.get("result3");
        resultMap.put("personInfo",name + " : " + address);
    }
}
