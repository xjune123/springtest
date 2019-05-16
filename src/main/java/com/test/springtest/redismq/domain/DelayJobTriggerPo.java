package com.test.springtest.redismq.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*
*  @author 
*/
@Data
public class DelayJobTriggerPo implements Serializable {

    private static final long serialVersionUID = 1554367340955L;


    /**
    * 主键
    * 表ID，主键，供其他表做外键
    * isNullAble:0
    */
    private Long id;

    /**
    * JOB ID
    * isNullAble:0
    */
    private Long jobId;

    /**
    * 实际开始时间
    * isNullAble:1
    */
    private Date actualStartTime;

    /**
    * 实际结束时间
    * isNullAble:0
    */
    private Date actualEndTime;

    /**
    * 轮循返回结果
    * isNullAble:0
    */
    private Long triggerStatus;

    /**
    * 执行类名
    * isNullAble:0
    */
    private String executorHandler;

    /**
    * 执行类参数
    * isNullAble:1
    */
    private String executorParam;

}
