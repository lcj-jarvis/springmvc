package com.mrlu.ssm.dao;

import com.mrlu.ssm.domain.Person;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-15 9:16
 */
public interface PersonDao {
    int insertPerson(Person person);
    List<Person> selectPersons();
}
