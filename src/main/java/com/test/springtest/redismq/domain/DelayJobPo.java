package com.test.springtest.redismq.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*
*  @author 
*/
@Data
public class DelayJobPo implements Serializable {

    private static final long serialVersionUID = 1554367329487L;

    /**
    * 主键
    * 表ID，主键，供其他表做外键
    * isNullAble:0
    */
    private Long id;

    /**
    * JOB名称
    * isNullAble:0
    */
    private String jobName;

    /**
    * 开始时间
    * isNullAble:0
    */
    private Date startTime;

    /**
    * 终止时间
    * isNullAble:0
    */
    private Date endTime;

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

    /**
    * 预警邮件
    * isNullAble:1
    */
    private String alarmEmail;

    /**
    * 轮循次数
    * isNullAble:0,defaultVal:1
    */
    private Long repeatCount;

    /**
    * 重复间隔时间
    * isNullAble:0
    */
    private Long repeatInterval;

    /**
    * 重复时间单位
    * isNullAble:0
    */
    private String repeatTimeUnit;

    /**
    * 实际轮循实际
    * isNullAble:1,defaultVal:0
    */
    private Long actualRepeatCount;

    /**
    * 创建时间
    * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
    */
    private Date createTime;

    /**
    * 更新时间
    * isNullAble:0,defaultVal:CURRENT_TIMESTAMP
    */
    private Date updateTime;


}
