package com.test.springtest.transaction.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Demo class
 *
 * @author junqiang.xiao
 * @date 2019/4/26 下午4:05
 */
@Table(name = "test2")
@Data
public class Test2 {
    @Id
    @GeneratedValue(generator = "JDBC")
    Long id;
    String name;
}
