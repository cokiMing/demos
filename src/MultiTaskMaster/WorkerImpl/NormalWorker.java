package MultiTaskMaster.WorkerImpl;

import MultiTaskMaster.Master;
import MultiTaskMaster.Worker;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class NormalWorker extends Worker {

    public NormalWorker(Master master){
        super(master);
    }

    @Override
    public void handler() throws Exception {
        System.out.println("normalWorker is working");
    }
}
