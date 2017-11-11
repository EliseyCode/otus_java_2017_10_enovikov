package ru.enovikow.otus;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private int size;
    private Object[] elementArray;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elementArray = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementArray, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        ensureCapacity(size + 1);
        elementArray[size++] = t;
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elementArray[index];
    }

    public T set(int index, T element) {
        ensureCapacity(index);

        T oldElement = (T) elementArray[index];
        elementArray[index] = element;
        return oldElement;
    }

    public void add(int index, T element) {

    }

    public T remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return new MyArrayListItr();
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elementArray.length > 0) {
            incrementArray(minCapacity);
        }
    }

    private void incrementArray(int minCapacity) {
        int oldCapacity = elementArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }

        elementArray = Arrays.copyOf(elementArray, newCapacity);
    }

    private class MyArrayListItr implements ListIterator<T> {

        int currentId;
        int prevRetId = -1;

        @Override
        public boolean hasNext() {
            return currentId != size;
        }

        @Override
        public T next() {
            if (currentId >= size) {
                throw new NoSuchElementException();
            }
            currentId++;
            return (T) elementArray[prevRetId = currentId - 1];
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {
            if (prevRetId < 0) {
                throw new IllegalStateException();
            }
            try {
                MyArrayList.this.set(prevRetId, t);
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void add(T t) {

        }
    }

}