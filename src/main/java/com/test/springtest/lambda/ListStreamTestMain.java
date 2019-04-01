package com.test.springtest.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 测试LIST 遍历和 STREAM 效率
 *
 * @author junqiang.xiao@hand-china.com
 * @date 2018/8/8
 */
public class ListStreamTestMain {
    public static void main(String[] args) {
        Person person;
        Random random = new Random();
        Integer age = null;
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            person = new Person();
            person.setFullName("C");
            age = random.nextInt(100);
            person.setAge(age);
            person.setBirthDate(new Date());
            personList.add(person);
        }
        System.out.println("age:" + age);
        List<Person> resultPersonList = new ArrayList<>();
        Date startDate = new Date();
        for (Person person1 : personList) {
            if (person1.getAge().equals(age)) {
                resultPersonList.add(person1);
            }
        }
        Date endDate = new Date();
        System.out.println("for循环时间(ms):" + (endDate.getTime() - startDate.getTime()) );
        System.out.println("for循环时间size:" + resultPersonList.size());

        System.out.println("resultPersonList:" + resultPersonList);

        final Integer filterAge = age;
        startDate = new Date();
        List<Person> streamPersonList = personList.stream().filter(per -> per.getAge().equals(filterAge)).collect(Collectors.toList());
        endDate = new Date();
        System.out.println("#################" );

        System.out.println("stream循环时间(ms):" + (endDate.getTime() - startDate.getTime()) );
        System.out.println("stream循环时间size:" + streamPersonList.size());

        System.out.println("resultPersonList:" + streamPersonList);

        startDate = new Date();
        List<Person> parallelStreamPersonList = personList.parallelStream().filter(per -> per.getAge().equals(filterAge)).collect(Collectors.toList());
        endDate = new Date();
        System.out.println("#################" );

        System.out.println("parallelStream循环时间(ms):" + (endDate.getTime() - startDate.getTime()) );
        System.out.println("parallelStream循环时间size:" + parallelStreamPersonList.size());

        System.out.println("parallelStreamPersonList:" + parallelStreamPersonList);

    }
}
