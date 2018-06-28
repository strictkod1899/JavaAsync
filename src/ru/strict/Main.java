package ru.strict;

import ru.strict.Tasks.*;
import java.util.concurrent.*;

public class Main {

    /**
     * <pre>
     * Принцип выполнения программы:
     * 1. Программа запускает метод asyncExecutionUsingGetMethod().
     *      Этот метод запустит асинхронное выполнение задач (Task),
     *      однако он будет ожидать возвращения от них результата в порядке вызова методов get().
     *      Это означает, что задачи (Task) выполнять все действия, описанные в методе call(),
     *      но программа получит результат от них, строго в том порядке, в котором вызывается метод get().
     * 2. Программа запускает метод asyncExecution().
     *      Т.к. метод выполняет задачи асинхронно, то он не будет ожидать завершения каждой из них
     *      - он просто запустить их и программа продолжит свое выполнение.
     * 3. Методы внутри asyncExecution() еще не выполнились, а программа перешла к запуску sequentialExecution().
     *      Эти методы выполнятся последовательно, то есть при вызове каждой задачи, программа будет ждать их завершения.
     * </pre>
     */
    public static void main(String[] args) {
        System.out.println("\n\t___________");
        asyncExecutionUsingGetMethod();
        System.out.println("\n\t___________");
        asyncExecution();
        System.out.println("\n\t___________");
        sequentialExecution();

        System.out.println("\n\t___________");
        System.out.println("\tEnd Main");
    }

    /**
     * Задачи выполнятся последовательно
     */
    public static void sequentialExecution(){
        System.out.println("\tStart method: sequentialExecution\n");
        FutureTask<String> taskShort = new FutureTask(new TaskShort("Sequential short task"));
        FutureTask<String> taskMedium = new FutureTask(new TaskMedium("Sequential medium task"));
        FutureTask<String> taskLong = new FutureTask(new TaskLong("Sequential long task"));

        taskShort.run();
        taskMedium.run();
        taskLong.run();
    }

    /**
     * Задачи выполнятся асинхронно.
     * Запуск задач произойдет последовательно, но они будут выполнены асинхронно
     */
    public static void asyncExecution(){
        System.out.println("\tStart method: asyncExecution\n");
        FutureTask<String> taskShort = new FutureTask(new TaskShort("Async short task"));
        FutureTask<String> taskMedium = new FutureTask(new TaskMedium("Async medium task"));
        FutureTask<String> taskLong = new FutureTask(new TaskLong("Async long task"));

        ExecutorService pool = Executors.newFixedThreadPool(3);
        // Завершит выполнение третьим
        pool.submit(taskLong);
        // Завершит выполнение вторым
        pool.submit(taskMedium);
        // Завершит выполнение первым
        pool.submit(taskShort);
    }

    /**
     * Задачи запустятся и выполнятся асинхронно, но вернут результат в последовательности вызова метода get
     */
    public static void asyncExecutionUsingGetMethod(){
        System.out.println("\tStart method: asyncExecutionUsingGetMethod\n");
        FutureTask<String> taskShort = new FutureTask(new TaskShort("Future.get() short task"));
        FutureTask<String> taskMedium = new FutureTask(new TaskMedium("Future.get() medium task"));
        FutureTask<String> taskLong = new FutureTask(new TaskLong("Future.get() long task"));

        ExecutorService pool = Executors.newFixedThreadPool(3);
        // Завершит выполнение третьим
        pool.submit(taskLong);
        // Завершит выполнение вторым
        pool.submit(taskMedium);
        // Завершит выполнение первым
        pool.submit(taskShort);

        try {
            String m3 = taskLong.get();
            System.out.println("Return: " + m3);
            String m1 = taskShort.get();
            System.out.println("Return: " + m1);
            String m2 = taskMedium.get();
            System.out.println("Return: " + m2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
