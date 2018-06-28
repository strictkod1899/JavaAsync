package ru.strict.Tasks;

import java.util.concurrent.Callable;

public class TaskShort implements Callable<String> {

    private String message;

    public TaskShort(String message){
        this.message = message;
    }

    @Override
    public String call() throws Exception{
        System.out.println("Start - " + message);
        Thread.sleep(2000);
        System.out.println("End - " + message);
        return message;
    }

}
