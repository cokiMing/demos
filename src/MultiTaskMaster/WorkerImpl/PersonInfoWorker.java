package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class PersonInfoWorker extends Worker{
    @Override
    public void handler() throws Exception {
        //获取输入参数
        String name = (String)resultMap.get("result1");
        String address = (String)resultMap.get("result3");

        /**
         * TODO 业务逻辑
         */
        Thread.sleep(100);

        //将结果放入结果集
        resultMap.put("personInfo",name + " : " + address);
    }
}
