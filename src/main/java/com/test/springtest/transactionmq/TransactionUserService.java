package com.test.springtest.transactionmq;

import com.test.springtest.test.User;
import com.test.springtest.transaction.domain.Test;
import com.test.springtest.transaction.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/7/10 下午4:35
 */
@Service
@Slf4j
public class TransactionUserService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private TestMapper testMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertUser() {
        log.info("[TransactionUserService] start insert foo");
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
        eventPublisher.publishEvent(new TransactionEvent("test", this));
        eventPublisher.publishEvent(new TransactionEvent2("test", this));

        log.info("[TransactionUserService] finish insert foo");
    }
}
