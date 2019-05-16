package com.test.springtest.redismq;

import com.test.springtest.redismq.dto.DelayJobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/4/1 下午3:15
 */
@Service
public class SendRedisDelayQueue {
    @Autowired
    DelayJobService delayJobService;
    @Autowired
    SendRedisMessage sendMessage;

    public void Send(String orderId,Long delayTime) {
        Map param = new HashMap();
        param.put("orderId", orderId);
        DelayJobDto delayJob = new DelayJobDto();
        delayJob.setJobParams(param);
        delayJob.setJobName(SendRedisMessage.class.getName());
        delayJob.setRepeatInterval(delayTime);
        delayJob.setRepeatTimeUnit(TimeUnit.SECONDS.name());
        delayJob.setStartTime(new Date());
        delayJob.setJobParams(param);
        System.out.println("Send redis Message:"+new Date());
        delayJobService.submitJob(delayJob);
    }
}
