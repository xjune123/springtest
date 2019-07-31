package com.test.springtest.transaction.service.impl;

import com.test.springtest.transaction.domain.Test;
import com.test.springtest.transaction.domain.Test2;
import com.test.springtest.transaction.mapper.Test2Mapper;
import com.test.springtest.transaction.mapper.TestMapper;
import com.test.springtest.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * service
 *
 * @author junqiang.xiao
 * @date 2019/4/22 下午2:19
 */
@Service
public class TransactionalServiceImpl implements TransactionService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private Test2Mapper test2Mapper;
    @Autowired
    private TransactionalService2Impl transactionalService2;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 异常
     * service1 insert
     * service2 insert
     * service2 throw exception
     * service1 和service2事务均回滚
     * <p>
     * 异常
     * service1 insert
     * service2 insert
     * service1 throw exception
     * service1 回滚，service2不回滚
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void insert() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
        transactionalService2.insert();
        throw new RuntimeException("异常");
    }

    /**
     * 无事务处理将同时插入两条记录也不回滚
     */
    @Override
    public void insertBatch() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
        Test2 test2 = new Test2();
        test2.setName("hi2：" + format);
        test2Mapper.insertSelective(test2);
        throw new RuntimeException("异常");

    }


    /**
     * 测试在数据库提交线程之前，线程执行情况
     * 测试结果
     * 1. 若数据库commit之前，线程已经执行，会查询不到最新记录
     * 2. Async 在同一个方法中不起作用，同理为Transaction
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertAsync() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);

        testMapper.insertSelective(test);
        System.out.println("after insert1" + new Date());

        transactionalService2.asyncJob(test.getId());
        System.out.println("after insert1.1" + new Date());
        //asyncJob(test.getId()) //该方式无法正常切入切面，因为是同方法调用aop this指的是当前类而不是代理类
        TransactionService self2 = applicationContext.getBean(TransactionService.class);
        self2.asyncJob(test.getId());
        System.out.println("after insert1.2" + new Date());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Test test2 = new Test();
        test2.setName("hi：" + format);
        testMapper.insertSelective(test2);
        System.out.println("after insert2.1" + new Date());

    }


    /**
     * 方法中执行insert 在新方法直接提交异步线程
     */
    @Override
    public void insertOkAsync() {
        Long id = insertTest();
        transactionalService2.asyncJob(id);
        System.out.println("after insert2.1" + new Date());

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertTest() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);

        testMapper.insertSelective(test);
        System.out.println("after insert1" + new Date());
        return test.getId();
    }

    @Async("asyncServiceExecutor")
    @Override
    public void asyncJob(Long id) {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Test test = testMapper.selectByPrimaryKey(id);
        System.out.println("GET TEST1:" + test + new Date());
    }

    /**
     * 事务会传递到内层，直至整个方法执行完毕
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void standardInsertBatch() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
        insertSecond();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSecond() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
    }

    /**
     * 测试service1 填写注解，同方法调用
     * 由于insertRequiresNew抛出异常，并且 insertRequiresNew和insert2为同一个service，数据均回滚
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertRequiresNew() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi：" + format);
        testMapper.insertSelective(test);
        insert2();
        throw new RuntimeException("异常");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert2() {
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi2：" + format);
        testMapper.insertSelective(test);
    }

    /**
     * 第一个方法不填写事务
     * 第二个方法填写事务
     * 第三个方法不填写事务，并且为private 方法
     * 第一个方法由于是执行本地方法，随后所有方法均不执行事务切面
     */
    @Override
    public void notFirstInsert(){
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi1：" + format);
        testMapper.insertSelective(test);
        notFirstInsert2();
        //末尾抛出异常，以上均会插入成功，因为事务切面已经结束
    }
    @Transactional(rollbackFor = Exception.class)
    public void notFirstInsert2(){
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi2：" + format);
        testMapper.insertSelective(test);
        notFirstInsert3();
        throw new RuntimeException("异常");
    }
    private void notFirstInsert3(){
        Test test = new Test();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        test.setName("hi3：" + format);
        testMapper.insertSelective(test);
    }


}
