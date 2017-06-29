package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class StoppingState extends LiftState{

    @Override
    public void stop() {
        System.out.println("电梯停止了");
    }

}
