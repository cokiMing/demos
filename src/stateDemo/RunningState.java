package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class RunningState extends LiftState{

    @Override
    public void run() {
        System.out.println("电梯启动了");
    }
}
