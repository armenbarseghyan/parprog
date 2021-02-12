package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Integer n =7;
        Console console = new Console();
       mSystem system = new mSystem(console);

        Thread threadS = new Thread(system, "system");


        for (int i = 0; i < n; i++) {
           try {
                    User p = new User(console, " сообщение от пользователя №"+ (i+1));
                    Thread t = new Thread(p, "пользователь №"+ (i+1));
                    t.start();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
        }
        threadS.start();

    }


}
