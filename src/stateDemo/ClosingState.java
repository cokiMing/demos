package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class ClosingState extends LiftState{

    @Override
    public void close() {
        System.out.println("电梯关上了");
    }

}
