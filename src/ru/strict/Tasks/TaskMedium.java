package ru.strict.Tasks;

import java.util.concurrent.Callable;

public class TaskMedium implements Callable<String> {

    private String message;

    public TaskMedium(String message){
        this.message = message;
    }

    @Override
    public String call() throws Exception{
        Thread.sleep(5000);
        System.out.println(message);
        return message;
    }

}
