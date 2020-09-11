package com.example.mapper;

import com.example.domain.Order;
import com.example.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select * from orders where uid=#{uid}")
    public  List<Order> findByUid();

    @Select("select * from orders")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "total", property = "total"),
            @Result(column = "ordertime", property = "orderTime"),
            @Result(
                    column = "uid",      // 根据哪一个字段去查询user
                    property = "user",  // 要封装的属性名称
                    javaType = User.class,
                    one = @One(select = "com.example.mapper.UserMapper.findById")
            )
    })
    public List<Order> findAll();

//    @Select("select *,o.id oid from orders o,users u where o.uid=u.id")
//    @Results({
//            @Result(column = "oid", property = "id"),
//            @Result(column = "total", property = "total"),
//            @Result(column = "ordertime", property = "orderTime"),
//            @Result(column = "uid", property = "user.id"),
//            @Result(column = "username", property = "user.username"),
//            @Result(column = "password", property = "user.password"),
//            @Result(column = "birthday", property = "user.birthday"),
//    })
//    public List<Order> findAll();
}
