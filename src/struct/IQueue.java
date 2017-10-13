package struct;

/**
 * Created by wuyiming on 2017/9/27.
 */
public interface IQueue<E> {

    void initQueue();

    void clearQueue();

    boolean isEmpty();

    E getHead();

    void enQueue(E e);

    E deQueue();

    int getSize();
}
