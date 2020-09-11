package com.example.test;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MapperTest {

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 向数据库插入一条数据，通过TypeHandler自动完成Date类型的转换
        User user = new User();
        user.setUsername("Test");
        user.setPassword("test");
        user.setBirthday(new Date());
        mapper.save(user);

        sqlSession.commit();
        sqlSession.close();
        
        // 从数据库获取一条数据，并通过TypeHandler自动完成Date类型的转换
        User userRes = mapper.findByID(6);
        System.out.println(userRes.getBirthday());
    }
    
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 设置分页相关的参数，设置页码和每一页的条数
        PageHelper.startPage(3, 3);

        List<User> userList = mapper.findAll();
        for (User user:userList) {
            System.out.println(user);
        }

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每一页显示的条数："+pageInfo.getPages());
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数"+pageInfo.getPages());
        System.out.println("上一页"+pageInfo.getPrePage());
        System.out.println("下一页"+pageInfo.getNextPage());
        System.out.println("是否是第一页"+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页"+pageInfo.isIsLastPage());

        sqlSession.close();
    }
}
