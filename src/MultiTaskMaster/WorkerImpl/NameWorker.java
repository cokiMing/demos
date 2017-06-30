package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class NameWorker extends Worker {
    @Override
    public void handler() throws Exception{
        String result1 = (String)argsMap.get("name");

//        异常测试
//        int i = 1/0;

        result1 = "小鸣";
        //TODO 业务代码
        Thread.sleep(100);

//        业务失败后除了将失败原因放入失败集以外，还应加return返回
//        failMap.put(this.getClass().getName(),"用户名异常");
//        return;

        resultMap.put("result1",result1);
    }
}
