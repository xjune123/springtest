package com.test.springtest.test;

import java.math.BigDecimal;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/3/28
 */
public class User {
    private Long userId;
    private BigDecimal salary;
    private String name;
    private Double bonus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", bonus=" + bonus +
                '}';
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
