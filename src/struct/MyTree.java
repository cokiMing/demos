package struct;

/**
 * Created by wuyiming on 2017/9/27.
 */
public class MyTree<E> implements ITree<E> {

    private int depth;

    private Leaf<E> root;

    @Override
    public void initTree() {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int depth() {
        return depth;
    }

    @Override
    public E getRoot() {
        return null;
    }

    @Override
    public E getParent(E child) {
        return null;
    }

    @Override
    public E getLeftChild(E parent) {
        return null;
    }

    @Override
    public E getRightChild(E parent) {
        return null;
    }

    @Override
    public E getRightSibling(E leaf) {
        return null;
    }

    @Override
    public void delete(E e) {

    }

    class Leaf<E> {
        E content;
        Leaf<E> parent;
        Leaf<E> left;
        Leaf<E> right;

        public Leaf(E e){
            content = e;
        }
    }
}
