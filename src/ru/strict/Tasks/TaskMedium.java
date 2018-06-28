package ru.strict.Tasks;

import java.util.concurrent.Callable;

public class TaskMedium implements Callable<String> {

    private String message;

    public TaskMedium(String message){
        this.message = message;
    }

    @Override
    public String call() throws Exception{
        System.out.println("Start - " + message);
        Thread.sleep(5000);
        System.out.println("End - " + message);
        return message;
    }

}
