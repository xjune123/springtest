package com.test.springtest.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Demo class
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/8/1
 */
public class LambdaSortMain {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.setFullName("A");
        person1.setAge(10);
        person1.setBirthDate(new Date());

        Person person2 = new Person();
        person2.setFullName("A");
        person2.setAge(5);
        person2.setBirthDate(new Date());


        Person person3 = new Person();
        person3.setFullName("B");
        person3.setAge(10);
        person3.setBirthDate(new Date());


        Person person4 = new Person();
        person4.setFullName("C");
        person4.setAge(10);
        person4.setBirthDate(new Date());


        Person person5 = new Person();
        person5.setFullName("C");
        person5.setAge(10);
        Date date = new Date(System.currentTimeMillis());
        person5.setBirthDate(new Date(((date.getTime()) / 1000 - 100)));

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        System.out.println("before");
        for (Person person : personList) {
            System.out.println(person);
        }
        personList.sort(Comparator.comparing(Person::getFullName).thenComparingInt(Person::getAge).thenComparing(Person::getBirthDate, Comparator.comparingLong(Date::getTime)));
        System.out.println("after");
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
