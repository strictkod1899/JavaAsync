package ru.strict.Tasks;

import java.util.concurrent.Callable;

public class TaskLong implements Callable<String> {

    private String message;

    public TaskLong(String message){
        this.message = message;
    }

    @Override
    public String call() throws Exception{
        Thread.sleep(10000);
        System.out.println(message);
        return message;
    }

}
