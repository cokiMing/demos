package stateDemo;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Main {

    public static void main(String args[]){
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
    }
}
