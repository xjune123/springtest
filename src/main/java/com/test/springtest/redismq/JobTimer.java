package com.test.springtest.redismq;

import com.test.springtest.redismq.dto.DelayJobDto;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class JobTimer {

    static final String jobsTag = "customer_jobtimer_jobs";
    @Autowired
    private RedissonClient client;

    @Autowired
    private ApplicationContext context;

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    @PostConstruct
    public void startJobTimer() {

        RBlockingQueue blockingQueue = client.getBlockingQueue(jobsTag);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        DelayJobDto job = (DelayJobDto) blockingQueue.take();
                        executorService.execute(new ExecutorTask(context, job));
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            TimeUnit.SECONDS.sleep(60);
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        }.start();
    }

    class ExecutorTask implements Runnable {

        private ApplicationContext context;

        private DelayJobDto delayJob;

        public ExecutorTask(ApplicationContext context, DelayJobDto delayJob) {
            this.context = context;
            this.delayJob = delayJob;
        }

        @Override
        public void run() {
            ExecuteJob service = (ExecuteJob) context.getBean(delayJob.getJobName());
            service.execute(delayJob);
        }

        private void check() {

        }
    }
}