package struct;

import entity.Base;

import java.util.NoSuchElementException;

/**
 * Created by wuyiming on 2017/9/26.
 */
public class LinkedStackDemo<E> implements IStack<E>{

    private Node<E> first;

    private Node<E> last;

    private int size;

    public LinkedStackDemo() {
        first = new Node<>(null);
        size = 0;
    }

    @Override
    public void push(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            last = node;
            first.next = last;
            last.prev = first;
        } else {
            last.next = node;
            node.prev = last;
            node.next = null;
            last = node;
        }
        size ++;
    }

    @Override
    public E pop() {
        if (size > 0) {
            Node<E> node = last;
            removeLast();
            return node.content;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void destroy() {
        Node<E> node = last;
        while (node != null) {
            node.next = null;
            node = node.prev;
        }
    }

    private void removeLast() {
        Node<E> node = last;
        last = last.prev;
        node.prev = null;
        last.next = null;
        size --;
    }

    @Override
    public void clear() {

    }

    @Override
    public E getTop() {
        return last.content;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node<E> {

        Node(E e) {
            content = e;
        }

        E content;
        Node<E> prev;
        Node<E> next;
    }

    public static void main(String[] args) {
        LinkedStackDemo<Base> linkedStack = new LinkedStackDemo<>();
        Base base1 = new Base();
        Base base2 = new Base();
        base1.setName("hello1");
        base2.setName("hello2");
        linkedStack.push(base1);
        linkedStack.push(base2);
        Base pop = linkedStack.pop();
        linkedStack.destroy();
        System.out.println(linkedStack);
    }
}
