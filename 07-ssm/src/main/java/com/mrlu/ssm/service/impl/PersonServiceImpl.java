package com.mrlu.ssm.service.impl;

import com.mrlu.ssm.dao.PersonDao;
import com.mrlu.ssm.domain.Person;
import com.mrlu.ssm.service.PersonService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-02-15 9:23
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    //注入批量执行的SqlSession
    @Autowired
    private SqlSession sqlSession;

    @Transactional
    @Override
    public int addPerson(Person person) {
        //测试事务
        int i = 100 / 0;
        return personDao.insertPerson(person);
    }


    @Override
    public List<Person> queryPersons() {
        return personDao.selectPersons();
    }
}
