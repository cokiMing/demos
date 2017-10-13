package struct;

import entity.Base;

/**
 * Created by wuyiming on 2017/9/26.
 */
public class StackDemo<E> implements IStack<E>{

    private Object[] element;

    private int position;

    private final static int DEFAULT_SIZE = 16;

    public StackDemo() {
        element = new Object[DEFAULT_SIZE];
        position = 0;
    }

    public void push(E e) {
        if (position >= element.length - 1){
            throw new ArrayIndexOutOfBoundsException();
        }
        element[position ++] = e;
    }

    public E pop() {
        final E e = (E)element[position - 1];
        element[-- position] = null;
        return e;
    }

    public void destroy() {
        for (int i = 0; i < position; i ++) {
            element[i] = null;
        }
    }

    public void clear() {
        for (int i = 0; i < position; i ++) {
            element[i] = null;
        }
        position = 0;
    }

    public E getTop() {
        return (E)element[position - 1];
    }

    public int size() {
        return element.length;
    }

    public static void main(String[] args) {
        StackDemo<Base> stackDemo = new StackDemo<>();
        Base base = new Base();
        base.setName("hello");
        stackDemo.push(base);
        System.out.println(stackDemo.position);
        Base pop = stackDemo.getTop();
        System.out.println("pop is: " + pop + " size :" + stackDemo.size());
    }
}
