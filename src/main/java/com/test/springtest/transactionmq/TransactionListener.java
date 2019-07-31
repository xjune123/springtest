package com.test.springtest.transactionmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 时间监听
 *
 * @author junqiang.xiao
 * @date 2019/7/10 下午5:03
 */
@Component
@Slf4j
public class TransactionListener {
    @Order(2)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hanldeOrderCreatedEvent(TransactionEvent event) {
        log.info("transactionEventListener start");
//        do transaction event
        log.info("event : " + event.getName());
//        finish transaction event
        log.info("transactionEventListener finish");
    }
    @Order(1)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hanldeOrderCreatedEvent2(TransactionEvent2 event) {
        log.info("transactionEventListener2 start");
//        do transaction event
        log.info("event2 : " + event.getName());
//        finish transaction event
        log.info("transactionEventListener2 finish");
    }
}
