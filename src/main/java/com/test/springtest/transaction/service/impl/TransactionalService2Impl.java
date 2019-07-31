package com.test.springtest.transaction.service.impl;

import com.test.springtest.transaction.domain.Test;
import com.test.springtest.transaction.domain.Test2;
import com.test.springtest.transaction.mapper.Test2Mapper;
import com.test.springtest.transaction.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * service2
 *
 * @author junqiang.xiao
 * @date 2019/4/26 下午3:50
 */
@Service
public class TransactionalService2Impl {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private Test2Mapper test2Mapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insert() {
        Test2 test = new Test2();
        String format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi2："+format);
        test2Mapper.insertSelective(test);
    }
    @Async("asyncServiceExecutor")
    public void asyncJob(Long id) {
        /*try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Test test = testMapper.selectByPrimaryKey(id);
        System.out.println("GET TEST2:"+test+new Date());
    }
}
