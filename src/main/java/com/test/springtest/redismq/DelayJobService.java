package com.test.springtest.redismq;

import com.alibaba.fastjson.JSONObject;
import com.test.springtest.redismq.domain.DelayJobPo;
import com.test.springtest.redismq.dto.DelayJobDto;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DelayJobService {

    @Autowired
    private RedissonClient client;

    public void submitJob(DelayJobDto job) {
        RBlockingQueue blockingQueue = client.getBlockingQueue(JobTimer.jobsTag);
        RDelayedQueue delayedQueue = client.getDelayedQueue(blockingQueue);
        DelayJobPo delayJobPo = new DelayJobPo();
        delayJobPo.setExecutorParam(JSONObject.toJSONString(job.getExecutorParam()));
        delayJobPo.setJobName(SendRedisMessage.class.getName());
        delayJobPo.setRepeatInterval(job.getRepeatInterval());
        delayJobPo.setRepeatTimeUnit(job.getRepeatTimeUnit());
        delayJobPo.setStartTime(new Date());
        delayedQueue.offer(job, job.getRepeatInterval(), TimeUnit.valueOf(job.getRepeatTimeUnit()));
    }

}