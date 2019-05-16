package com.test.springtest.redismq.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class DelayJobDto {
    private static final long serialVersionUID = 1L;

    private Map jobParams;//job执行参数
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

}