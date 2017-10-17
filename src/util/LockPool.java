package util;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>锁池</p>
 * 用于特殊并发场景下的锁管理
 * Created by wuyiming on 2017/10/17.
 */
public class LockPool<L> {
    //内部锁队列
    private final Set<ExpireUnit<L>> lockArray = new HashSet<>();
    //锁的生命时长：ms
    private long expire = 0L;

    /**
     * @param expire 过期时长:ms
     */
    public LockPool(long expire) {
        this.expire = expire;
        Thread thread = new Thread("thread-lockManager") {
            @Override
            public void run() {
                while (true) {
                    Set<ExpireUnit> newArray = new HashSet<>();
                    for (ExpireUnit unit : lockArray) {
                        if (System.currentTimeMillis() - unit.expireTime > expire) {
                            newArray.add(unit);
                        }
                    }

                    if (!newArray.isEmpty()) {
                        synchronized (this) {
                            lockArray.removeAll(newArray);
                        }
                    }

                    try {
                        Thread.sleep(expire);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * <p>获取锁</p>
     * @apiNote
     * 获取锁时，若参数池中不存在参数对象，则将该对象放入锁池，并返回该对象；
     * 若已存在，则返回池中已有的对象锁，并更新存活时间；
     * @param lock 锁对象：需重写hashCode方法
     * @return 锁对象
     */
    public synchronized L obtainLock(L lock) {
        for (ExpireUnit<L> unit : lockArray) {
            if (unit.hashCode() == lock.hashCode()){
                unit.expireTime = System.currentTimeMillis();
                return unit.lock;
            }
        }

        ExpireUnit<L> unit = new ExpireUnit<>(lock,System.currentTimeMillis());
        lockArray.add(unit);
        return lock;
    }

    /**
     * 获取锁池的大小
     * @return 锁池的体积
     */
    public int size() {
        return lockArray.size();
    }

    /**
     * 内部单元类
     * @param <L>
     */
    private static class ExpireUnit<L> {
        private final L lock;
        private long expireTime;

        ExpireUnit(L lock, long expireTime) {
            this.lock = lock;
            this.expireTime = expireTime;
        }

        @Override
        public int hashCode() {
            return lock.hashCode();
        }
    }
}
