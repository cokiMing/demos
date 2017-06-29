package stateDemo;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Context {

    public final static OpenningState OPENNING_STATE = new OpenningState();
    public final static ClosingState CLOSING_STATE = new ClosingState();
    public final static StoppingState STOPPING_STATE = new StoppingState();
    public final static RunningState RUNNING_STATE = new RunningState();

    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        liftState.setContext(this);
    }

    public void open(){
        this.liftState.open();
    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }
}
