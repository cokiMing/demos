package struct;

/**
 * Created by wuyiming on 2017/9/27.
 */
public interface ITree<E> {

    void initTree();

    void clear();

    boolean isEmpty();

    int depth();

    E getRoot();

    E getParent(E child);

    E getLeftChild(E parent);

    E getRightChild(E parent);

    E getRightSibling(E leaf);

    void delete(E e);
}
