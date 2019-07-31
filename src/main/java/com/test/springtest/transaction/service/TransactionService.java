package com.test.springtest.transaction.service;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/5/17 下午5:49
 */
public interface TransactionService {
    void insert();

    void insertBatch();

    void insertAsync();

    void insertOkAsync();

    Long insertTest();

    void asyncJob(Long id);

    void standardInsertBatch();

    void insertSecond();

    void insertRequiresNew();

    void insert2();

    void notFirstInsert();
}
