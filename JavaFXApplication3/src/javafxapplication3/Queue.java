package javafxapplication3;

import java.util.LinkedList;

public class Queue<E> {
    LinkedList<E> list;

    public Queue(E[] e) {
        list = new LinkedList<>();
        for (int i = 0; i < e.length; i++) {
            list.addLast(e[i]);
        }
    }

    public Queue() {
        list = new LinkedList<>();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public E getElement(int i) {
        return list.get(i);
    }

    public E peek() {
        return list.getFirst();
    }

    public int getSize() {
        return list.size();
    }

    public boolean contains(E e) {
        return list.contains(e);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
