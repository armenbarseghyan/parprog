package ru.spbstu.telematics.java;


import com.google.common.collect.Multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class HashMultiSet<T> implements Multiset<T>{
    
    private int size;
    private Map<T, Integer> myList;

    public HashMultiSet() {
        this.size = 0;
        myList = new HashMap<>();
    }

    public HashMultiSet(Collection<T> copy) {
        this.size =0;
        myList = new HashMap<T, Integer>();
        for (T e : copy) {
            add(e);
        }
    }


    public int add(T e, int count) {
        if (myList.containsKey(e)) {
            myList.put(e, myList.get(e)+count);
            size += count;
            return count;
        }
        myList.put(e,count);
        size += count;
        return count;
    }

    @Override
    public boolean add(T e) {
        if (myList.containsKey(e)) {
            myList.put(e, myList.get(e)+1);
            size++;
            return true;
        }
        myList.put(e,1);
        size++;
        return true;

    }

    @Override
    public boolean remove(Object e) {
        if (myList.containsKey(e)) {
            myList.remove(e, 1);
            size--;
            if(count((T) e)==0)
                myList.remove(e);
            return true;
        }
        return false;

    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }


    public int remove(Object e, int count) {
        int i=0;
        while (myList.containsKey(e)&&count !=0){
            remove(e);
            count--;
            i++;
        }
        return i;
    }

    public int setCount(T t, int i) {
        int c = 0;
        if (myList.containsKey(t)){
            c = myList.get(t);
            if (i==c){
                return c;
            }else if (c>i){
                remove(t, c-i);
                return c;
            } else{
                add(t, i-c);
                return c;
            }
        }
        add(t, i);
        return 0;
    }


    public boolean setCount(T t, int i, int i1) {
        if (myList.get(t)!=i)
            return false;
        else {
            setCount(t, i1);
            return true;
        }
    }

    public Set<T> elementSet() {
        Set<T> res = new HashSet<>();
        for(Map.Entry<T, Integer> pair: myList.entrySet())
            res.add(pair.getKey());
        return res;
    }

    @Override
    public Set<Entry<T>> entrySet() {

            throw new UnsupportedOperationException();
        

    }



    public int count(Object o) {
        if(myList.containsKey((T) o)) {
            return myList.get((T)o);
        }
        return 0;
    }

    @Override
    public void clear() {
        myList.clear();
        size=0;
    }



    public boolean containsKey(T t) {
       return myList.containsKey(t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class HashMultiSetIterator implements Iterator<T>{

        private Iterator<Map.Entry<T,Integer>> myListIterator;
        private T key;
        private int count;
        private int index;

        public HashMultiSetIterator() {
            this.myListIterator = myList.entrySet().iterator();
            this.index = 0;
            this.count= 0;
        }


        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                if (count == 0) {
                    Map.Entry<T, Integer> entry = myListIterator.next();
                    count = entry.getValue();
                    key = entry.getKey();
                }

                index++;
                count--;
                return key;

            }else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    @Override
    public Iterator<T> iterator() {
        return new HashMultiSetIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        int i =0;
        for(Map.Entry<T, Integer> pair: myList.entrySet()){
            res[i] = pair.getKey();
            i++;
        }
        return res;
    }

    @Override
    public <T1> T1[] toArray( T1[] a) {
        T1[] res;
        if (size<=a.length){
            res =a;
        }
        else {
            res =(T1[]) new Object[size];
        }
        int i =0;
        for(Map.Entry<T, Integer> pair: myList.entrySet()){
            res[i] = (T1)pair.getKey();
            i++;
        }
        return res;
    }

    @Override
    public boolean contains(Object o) {
        return myList.get(o)!=0;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        int count = 0;
        for (Object element: collection)
            if(myList.get(element) == 0)
                break;
            else count++;
        return count == collection.size();
    }

    @Override
    public boolean addAll( Collection<? extends T> c) {
        for (T element: c)
            this.add(element);
        return true;
    }


}
