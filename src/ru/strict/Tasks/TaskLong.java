package ru.strict.Tasks;

import java.util.concurrent.Callable;

public class TaskLong implements Callable<String> {

    private String message;

    public TaskLong(String message){
        this.message = message;
    }

    @Override
    public String call() throws Exception{
        System.out.println("Start - " + message);
        Thread.sleep(10000);
        System.out.println("End - " + message);
        return message;
    }

}
