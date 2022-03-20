package com.mrlu.ssm.service;

import com.mrlu.ssm.domain.Person;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-15 9:22
 */
public interface PersonService {
    int addPerson(Person person);
    List<Person> queryPersons();
}
