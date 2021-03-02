package ru.spbstu.telematics.java;
import com.google.common.collect.HashMultiset;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;


public class HashMultiSetTest  extends TestSuite {
    private static HashMultiSet<String> myHashMultiSet = new HashMultiSet<>();
    private static HashMultiset<String> hashMultiset = HashMultiset.create();

    @Before
    public void init(){
        myHashMultiSet.add("AAA");
        myHashMultiSet.add("BBB");
        myHashMultiSet.add("AAA");

        hashMultiset.add("AAA");
        hashMultiset.add("BBB");
        hashMultiset.add("AAA");
    }
    @Test
    public void countTest() {

        Assertions.assertEquals(this.hashMultiset.count("AAA"), this.myHashMultiSet.count("AAA"));
    }

    @Test
    public void removeTest() {
        hashMultiset.remove("AAA", 2);
        myHashMultiSet.remove("AAA", 2);
        Assertions.assertEquals(hashMultiset.count("AAA"),myHashMultiSet.count("AAA"));
    }

    @Test
    public void containsKeyTest() {
        myHashMultiSet.add("BBB");
        hashMultiset.add("BBB");
        Assertions.assertEquals(hashMultiset.contains("BBB"), myHashMultiSet.containsKey("BBB"));

    }

    @Test
    public void iteratorTest() {
        Iterator<String> myHashMultiSetIterator = myHashMultiSet.iterator();
        Iterator<String> hashMultisetIterator = hashMultiset.iterator();

        Assertions.assertEquals(hashMultisetIterator.hasNext(), myHashMultiSetIterator.hasNext());

        if (hashMultisetIterator.hasNext()&& myHashMultiSetIterator.hasNext())
            Assertions.assertEquals(hashMultisetIterator.next(), myHashMultiSetIterator.next());

    }

    @Test
    public void setCountTest() {
        Assertions.assertEquals(hashMultiset.setCount("BBB", 0), myHashMultiSet.setCount("BBB", 0));
        Assertions.assertEquals(hashMultiset.setCount("BBB", 0, 8), myHashMultiSet.setCount("BBB", 0, 8));

    }
}
