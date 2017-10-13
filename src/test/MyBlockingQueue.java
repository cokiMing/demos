package test;

/**
 * Created by wuyiming on 2017/10/12.
 */
public class MyBlockingQueue<T> {

    private Object[] array = new Object[10];

    private volatile int size = 0;

    public synchronized void put(T t) {
        try {
            while ( size > 9) {
                this.wait();
            }

            array[size++] = t;
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized T take() {
        try {
            while (size == 0) {
                this.wait();
            }
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) array[--size];
    }
}
