package ru.spbstu.telematics.java;

import java.util.concurrent.Semaphore;

public class Console {
    Semaphore semaphore;

    Console (){
        semaphore = new Semaphore(1);

    }

     void WriteOnConsole(String str){
           try {
                semaphore.acquire();
                java.lang.System.out.println('\t'+Thread.currentThread().getName() + " получил доступ к консоли");
                Thread.sleep((long) (Math.random()%1000+500));
                java.lang.System.out.println(str);
                java.lang.System.out.println('\t'+Thread.currentThread().getName() + " закончил пользоваться консолью");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
