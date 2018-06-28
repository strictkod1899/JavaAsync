package ru.strict;

import ru.strict.Tasks.*;
import java.util.concurrent.*;

public class Main {

    /**
     * <pre>
     * Принцип выполнения программы:
     * 1. Программа запускает метод asyncExecution().
     *      Т.к. метод выполняет задачи асинхронно, то он не будет ожидать завершения каждой из них
     *      - он просто запустить их и программа продолжит свое выполнение.
     * 2. Методы внутри asyncExecution() еще не выполнились, а программа перешла к запуску sequentialExecution().
     *      Эти методы выполнятся последовательно, то есть при вызове каждой задачи, программа будет ждать их завершения.
     * 3. Т.к. у нас сначала были запущены асинхронные задачи, то мы увидим следующий вывод программы:
     *      <code><pre style="background-color: white; font-family: consolas">
     *          Async short task
     *          Sequential short task
     *          Async medium task
     *          Sequential medium task
     *          Async long task
     *          Sequential long task
     *      </pre></code>
     * </pre>
     */
    public static void main(String[] args) {
        asyncExecution();
        sequentialExecution();
    }

    /**
     * Методы выполнятся последовательно
     */
    public static void sequentialExecution(){
        FutureTask<String> taskShort = new FutureTask(new TaskShort("Sequential short task"));
        FutureTask<String> taskMedium = new FutureTask(new TaskMedium("Sequential medium task"));
        FutureTask<String> taskLong = new FutureTask(new TaskLong("Sequential long task"));

        taskShort.run();
        taskMedium.run();
        taskLong.run();
    }

    /**
     * Методы выполнятся асинхронно.
     * Запуск методов произойдет последовательно, но они будут выполнены асинхронно (словно параллельно)
     */
    public static void asyncExecution(){
        FutureTask<String> taskShort = new FutureTask(new TaskShort("Async short task"));
        FutureTask<String> taskMedium = new FutureTask(new TaskMedium("Async medium task"));
        FutureTask<String> taskLong = new FutureTask(new TaskLong("Async long task"));

        ExecutorService pool = Executors.newFixedThreadPool(3);
        // Завершит выполнение третьим
        pool.execute(taskLong);
        // Завершит выполнение вторым
        pool.execute(taskMedium);
        // Завершит выполнение первым
        pool.execute(taskShort);
    }
}
