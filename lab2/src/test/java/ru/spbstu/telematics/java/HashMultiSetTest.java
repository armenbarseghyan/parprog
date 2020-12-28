package ru.spbstu.telematics.java;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;


public class HashMultiSetTest {

    @Test
    public void putTest() {
        MultiSet<Integer> hashMultiSet = new HashMultiSet<>();
        int elementToPut = ThreadLocalRandom.current().nextInt();
        hashMultiSet.put(elementToPut);
        Assertions.assertEquals(hashMultiSet.get(elementToPut), 1);
        hashMultiSet.put(elementToPut);
        Assertions.assertEquals(hashMultiSet.get(elementToPut), 2);
    }

    @Test
    public void removeTest() {
        MultiSet<Integer> hashMultiSet = new HashMultiSet<>();
        int elementToPut = ThreadLocalRandom.current().nextInt();
        hashMultiSet.put(elementToPut);
        hashMultiSet.put(elementToPut);
        hashMultiSet.remove(elementToPut);
        Assertions.assertEquals(hashMultiSet.get(elementToPut), 1);
    }

    @Test
    public void containsKeyTest() {
        MultiSet<Integer> hashMultiSet = new HashMultiSet<>();
        int elementToPut = ThreadLocalRandom.current().nextInt();
        hashMultiSet.put(elementToPut);
        Assertions.assertTrue(hashMultiSet.containsKey(elementToPut));
    }

    @Test
    public void notContainsKeyTest() {
        MultiSet<Integer> hashMultiSet = new HashMultiSet<>();
        Assertions.assertFalse(hashMultiSet.containsKey(ThreadLocalRandom.current().nextInt()));
    }
}
