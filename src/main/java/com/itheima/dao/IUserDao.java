package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
* @Description 用户持久层
* @Params
* @Return
* @Author Xu Feng
* @Date 2019/9/15 12:03
**/
public interface IUserDao {
    /**
    * @Description 查找所有
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/15 12:03
    **/
    List<User> findAll();

    /**
    * @Description 根据姓名查找
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/26 21:42
    **/
    List<User> findByName(String name);

    /**
    * @Description 获取用户的金钱
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/26 23:24
    **/

    float getMoney(User user);

    /**
    * @Description 转账操作
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/26 21:58
    **/
    void updateByName(User user);

}
