package com.example.mapper;

import com.example.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("insert into users values(#{id},#{username},#{password},#{birthday}")
    public void save(User user);

    @Update("update users set username=#{username}, password=#{password} where id=#{id}")
    public void update(User user);

    @Delete("delete from user where id=#{id}")
    public void delete(int id);

    @Select("select * from users where id=#{id}")
    public User findById(int id);

    @Select("select * from users")
    public List<User> findAll();

    @Select("select * from users")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(
                    property="orderList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.example.mapper.OrderMapper.findByUid")
            )
    })
    public List<User> findUserAndOrderAll();
}
