package test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuyiming on 2017/10/12.
 */
public class User implements Delayed {

    private String name;

    private String id;

    private long amount;

    public User(String name, String id, long amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return amount - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        User user = (User)o;
        return getDelay(TimeUnit.SECONDS) >= user.getDelay(TimeUnit.SECONDS) ? 1 : -1;
    }
}
