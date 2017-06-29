package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class OpenningState extends LiftState{

    @Override
    public void open() {
        System.out.println("电梯打开了");
    }
}
