package com.allen.java.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolExcecutorTest
 * @Description: TODO
 * @author anjinlong
 * @date 2016年3月26日 下午11:47:00
 *
 */
public class ThreadPoolExcecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000));
        for (int i = 1; i < 51; i++) {
            UpsertTask upsertTask = new UpsertTask(i + "");
            executor.execute(upsertTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size()
                    + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();

    }
}

class UpsertTask implements Runnable {
    private String line;

    UpsertTask(String l) {
        this.line = l;
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(line + " finish ！");
    }
}