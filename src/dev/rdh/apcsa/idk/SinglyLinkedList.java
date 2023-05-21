package dev.rdh.apcsa.idk;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    public static class Node<T> {
        public T data;
        public Node<T> next;
        public Node(T data) {
            this.data = data;
        }
    }
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
    public void add(T data) {
        if(head == null) {
            head = new Node<>(data);
            return;
        }
        Node<T> current = head;
        while(current.next != null)
            current = current.next;
        current.next = new Node<>(data);
    }
    public void add(T data, int index) {
        if(index < 0) throw new IndexOutOfBoundsException("index cannot be negative");
        if(index == 0) {
            Node<T> temp = head;
            head = new Node<>(data);
            head.next = temp;
            return;
        }
        Node<T> current = head;
        for(int i = 0; i < index - 1; i++) {
            if(current.next == null) throw new IndexOutOfBoundsException("index is too large");
            current = current.next;
        }
        Node<T> temp = current.next;
        current.next = new Node<>(data);
        current.next.next = temp;
    }
    public void remove(int index) {
        if(index < 0) throw new IndexOutOfBoundsException("index cannot be negative");
        if(index == 0) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        for(int i = 0; i < index - 1; i++) {
            if(current.next == null) throw new IndexOutOfBoundsException("index is too large");
            current = current.next;
        }
        if(current.next == null) throw new IndexOutOfBoundsException("index is too large");
        current.next = current.next.next;
    }
    public void remove(T data) {
        if(head == null) return;
        if(head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        while(current.next != null) {
            if(current.next.data.equals(data)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }
    public T get(int index) {
        if(index < 0) throw new IndexOutOfBoundsException("index cannot be negative");
        Node<T> current = head;
        for(int i = 0; i < index; i++) {
            if(current == null) throw new IndexOutOfBoundsException("index is too large");
            current = current.next;
        }
        if(current == null) throw new IndexOutOfBoundsException("index is too large");
        return current.data;
    }
}
