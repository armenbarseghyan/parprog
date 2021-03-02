package ru.spbstu.telematics.java;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String [] args){
        HashMultiSet<String> hashMultiset= new HashMultiSet<>();


        hashMultiset.add("AAA");
        hashMultiset.add("BBB");
        hashMultiset.add("AAA");
        hashMultiset.add("BBB");
        hashMultiset.add("AAA");
        hashMultiset.add("AAA");
        hashMultiset.add("AAB");
        hashMultiset.add("ABA");
        hashMultiset.add("ABB");
        hashMultiset.add("BAA");
        hashMultiset.add("BAB");
        hashMultiset.add("BBA");
        hashMultiset.add("BBB");
        hashMultiset.add("AAB");
        hashMultiset.add("AAB");
        hashMultiset.add("AAB");
        hashMultiset.add("AAB");
        Set<String> set1 =hashMultiset.elementSet();

        HashMultiset <String>hashMultiset1 = HashMultiset.create();

        hashMultiset1.add("AAA");
        hashMultiset1.add("BBB");
        hashMultiset1.add("AAA");
        hashMultiset1.add("BBB");
        hashMultiset1.add("AAA");
        hashMultiset1.add("AAA");
        hashMultiset1.add("AAB");
        hashMultiset1.add("ABA");
        hashMultiset1.add("ABB");
        hashMultiset1.add("BAA");
        hashMultiset1.add("BAB");
        hashMultiset1.add("BBA");
        hashMultiset1.add("BBB");
        hashMultiset1.add("AAB");
        hashMultiset1.add("AAB");
        hashMultiset1.add("AAB");
        hashMultiset1.add("AAB");

        Set<Multiset.Entry<String>> set = hashMultiset1.entrySet();

        boolean f =hashMultiset1.contains("AAA");
        f = hashMultiset.contains("AAA");
        Object strings[] = hashMultiset1.toArray();
        System.out.println("Hello world!");
    }

}
