package ru.enovikow.otus;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }

    public Iterator<T> iterator() {
        throw new NotImplementedException();
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementArray, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new NotImplementedException();
    }

    public boolean add(T t) {
        ensureCapacity(size + 1);
        elementArray[size++] = t;
        return true;
    }

    public boolean remove(Object o) { throw new NotImplementedException();}

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
    }

    public T remove(int index) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public ListIterator<T> listIterator() {
        return new MyArrayListItr();
    }

    public ListIterator<T> listIterator(int index) {
        throw new NotImplementedException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
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
            throw new NotImplementedException();
        }

        @Override
        public T previous() {
            throw new NotImplementedException();
        }

        @Override
        public int nextIndex() {
            throw new NotImplementedException();
        }

        @Override
        public int previousIndex() {
            throw new NotImplementedException();
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
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
            throw new NotImplementedException();
        }
    }

}