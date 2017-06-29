package memento;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class Memento {

    private String status;

    private Object backup;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getBackup() {
        return backup;
    }

    public void setBackup(Object backup) {
        this.backup = backup;
    }
}
