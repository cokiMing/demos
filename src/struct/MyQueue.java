package struct;

/**
 * Created by wuyiming on 2017/9/27.
 */
public class MyQueue<E> implements IQueue<E> {

    private Object[] element;

    private int size;

    private int position;

    private final static int DEFAULT_SIZE = 16;

    @Override
    public void initQueue() {
        element = new Object[DEFAULT_SIZE];
        position = 0;
        size = DEFAULT_SIZE;
    }

    @Override
    public void clearQueue() {
        for (Object object: element) {
            object = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    @Override
    public E getHead() {
        return (E)element[0];
    }

    @Override
    public void enQueue(E e) {
        element[position ++] = e;
    }

    @Override
    public E deQueue() {
        E e = (E)element[0];
        Object[] newElement = new Object[size];
        System.arraycopy(element,1,newElement,0,position);
        element = newElement;
        position --;
        return e;
    }

    @Override
    public int getSize() {
        return position;
    }
}
