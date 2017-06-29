package MultiTaskMaster;

import MultiTaskMaster.WorkerImpl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Main {
    public static void main(String args[]){
        Result result = getCallInfo("xiaoming", 15, "China Zhejiang Hangzhou");
        System.out.println(result.getCode());
    }

    public static Result getCallInfo(String name,int age,String address){
        long millis = System.currentTimeMillis();
        try{
            //输入参数封装
            Map argsMap = new HashMap<String,Object>();
            argsMap.put("name",name);
            argsMap.put("age",age);
            argsMap.put("address",address);
            Master master = new Master(argsMap);

            //需要异步的任务设置
            Worker nameWorker = new NameWorker();
            Worker ageWorker = new AgeWorker();
            Worker addressWorker = new AddressWorker();
            master.setWorker(nameWorker);
            master.setWorker(ageWorker);
            master.setWorker(addressWorker);
            master.execute();

            //不需要异步的任务设置（按需封装）
            Worker normalWorker = new NormalWorker(master);
            normalWorker.handler();

            //检查任务是否有异常
            if(overLoop(master)){
                return Result.fail();
            }

            //装载下一轮任务
            Worker personInfoWorker = new PersonInfoWorker();
            master.setWorker(personInfoWorker);
            master.execute();

            if(overLoop(master)){
                return Result.fail();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Result.fail();
        }

        System.out.println("总耗时:" + (System.currentTimeMillis() - millis));
        return Result.success();
    }

    //等待线程运行结果，并判断是否需要中断
    private static boolean overLoop(Master master){

        while (!master.isComplete()){
            if (master.isException()){
                //TODO 异常处理
                master.printExceptions();
                return true;
            }
        }
        return false;
    }
}
