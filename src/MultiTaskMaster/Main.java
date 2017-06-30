package MultiTaskMaster;

import MultiTaskMaster.WorkerImpl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Main {
    public static void main(String args[]){
        long millis = System.currentTimeMillis();
        Result result = testMethod("xiaoming", 15, "China Zhejiang Hangzhou");
        System.out.println("总耗时:" + (System.currentTimeMillis() - millis));
        System.out.println(result);
    }

    public static Result testMethod(String name,int age,String address){
        try{
            //输入参数封装
            Map argsMap = new HashMap<String,Object>();
            argsMap.put("name",name);
            argsMap.put("age",age);
            argsMap.put("address",address);
            Master master = new Master(argsMap);

            /**
             * 这里设置的所有worker之间必须是没有参数关联的
             * 任何与本轮次其他任务结果或输入参数有关联的任务
             * 都应该设置到下一轮中
             */
            Worker nameWorker = new NameWorker();
            Worker ageWorker = new AgeWorker();
            Worker addressWorker = new AddressWorker();
            master.setWorker(nameWorker);
            master.setWorker(ageWorker);
            master.setWorker(addressWorker);
            master.execute();

            //需要异步但不需要监听结果及异常的任务
            {
                //TODO SOMETHING
            }

            //不需要异步的任务
            {
                //TODO SOMETHING
            }

            //监听任务是否有异常
            if(checkLoop(master)){
                String failMsg = master.getFailMessage();
                failMsg = failMsg == null ? "系统异常":failMsg;
                return Result.fail(failMsg);
            }

            //装载下一轮任务并执行
            Worker personInfoWorker = new PersonInfoWorker();
            master.setWorker(personInfoWorker);
            master.execute();

            if(checkLoop(master)){
                String failMsg = master.getFailMessage();
                failMsg = failMsg == null ? "系统异常":failMsg;
                return Result.fail(failMsg);
            }

            Result success = Result.success();
            success.put(master.getResultMap().get("personInfo"));
            return success;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.fail("正常任务错误");
        }
    }

    //等待线程运行结果，并判断是否需要中断
    private static boolean checkLoop(Master master){
        while (!master.isComplete()){
            /**
             * 以下异常及失败捕捉建议在调试时屏蔽，以方便异常的追踪和暴露
             * 不屏蔽的情况下可以实现对客户端的快速响应
             */
            if (master.isException()){
                master.printExceptions();
                return true;
            }
            if (master.isFail()){
                return true;
            }
        }
        if (master.isException() || master.isFail()){
            master.printExceptions();
            return true;
        }

        master.cleanWorkerList();
        master.cleanThreads();
        return false;
    }
}
