package ru.spbstu.telematics.java;

public class User implements Runnable {

    Console console;
    String message;

    User(Console c, String m){
        this.console=c;
        this.message = m;
    }

    @Override
    public void run() {

            try{
               // System.out.println(Thread.currentThread().getName()+" пытается написать что-то на консоли");

                console.WriteOnConsole(this.message);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

}
