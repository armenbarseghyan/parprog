package ru.spbstu.telematics.java;

public class mSystem implements Runnable{

    Console console;


    mSystem(Console c){ this.console=c;}

    @Override
    public void run() {

          try{
              //System.out.println("система пытается написать что-то на консоли");
                console.WriteOnConsole("сообщение системы");
          }catch (Exception e){
              System.out.println(e.getMessage());
         }
    }
}
