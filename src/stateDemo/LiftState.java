package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public abstract class LiftState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void open() {
        context.setLiftState(Context.OPENNING_STATE);
        context.open();
    }

    public void close() {
        context.setLiftState(Context.CLOSING_STATE);
        context.close();
    }

    public void stop() {
        context.setLiftState(Context.STOPPING_STATE);
        context.stop();
    }

    public void run() {
        context.setLiftState(Context.RUNNING_STATE);
        context.run();
    }

}
