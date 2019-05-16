package com.test.springtest.redismq;

import com.test.springtest.redismq.dto.DelayJobDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/4/1 下午3:33
 */
@Service
public class SendRedisMessage implements ExecuteJob {
    @Override
    public void execute(DelayJobDto job) {
        Map param = job.getJobParams();
        String orderId = (String) param.get("orderId");
        System.out.println("orderId:" + orderId + " Date:" + new Date());
    }
}
