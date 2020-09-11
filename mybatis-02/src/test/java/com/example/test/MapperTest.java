package com.example.test;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 测试findAll
//        List<User> userList = mapper.findAll();
//        System.out.println(userList);

        // 编写测试user对象
//        User condition = new User();
//        condition.setId(1);
//        condition.setUsername("yaokm");
//        condition.setPassword("123");
//        List<User> userList = mapper.findByCondition(condition);
//        System.out.println(userList);

        // 编写ids测试对象
        List<Integer> ids = new ArrayList<>();
        ids.add(1);

        List<User> userList = mapper.findByIDs(ids);
        System.out.println(userList);
    }
}
