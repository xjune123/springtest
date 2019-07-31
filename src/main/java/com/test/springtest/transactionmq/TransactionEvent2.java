package com.test.springtest.transactionmq;

import org.springframework.context.ApplicationEvent;

/**
 * TransactionalEventListener
 *
 * @author junqiang.xiao
 * @date 2019/7/10 上午11:32
 */
public class TransactionEvent2 extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    private String name;

    public TransactionEvent2(String name, Object source) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
