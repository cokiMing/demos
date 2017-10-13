package struct;

/**
 * Created by wuyiming on 2017/9/26.
 */
public interface IStack<E> {

    void push(E e);

    E pop();

    void destroy();

    void clear();

    E getTop();

    int size();
}
