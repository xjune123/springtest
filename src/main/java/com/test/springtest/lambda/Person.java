package com.test.springtest.lambda;

import java.util.Date;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/8/1
 */

public class Person {
    String fullName;
    Integer age;
    Date birthDate;

    public String getFullName() {
        return fullName;
    }

    public Person setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Person setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                '}';
    }
}
