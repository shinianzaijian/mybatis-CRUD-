package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import java.util.List;

public class TestMybatis {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws Exception{
        sqlSession.commit();
        in.close();
        sqlSession.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testFindByName(){
        User user = new User();
        user.setUsername("eee");
        List<User> users = userDao.findByName(user.getUsername());
        System.out.println(users.isEmpty()?null:users.get(0));
    }

    /**
    * @Description 获取用户的金钱
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/26 23:21
    **/

    public float getMoney(String name){
        User user = new User();
        user.setUsername(name);
        return userDao.getMoney(user);
    }

    /**
    * @Description 实现转账操作
    * @Params
    * @Return
    * @Author Xu Feng
    * @Date 2019/9/26 23:22
    **/

    public void Tranform(String topeople,String inpeople, Float money){
        User inuser = new User();
        inuser.setUsername(topeople);
        inuser.setMoney(this.getMoney(topeople)-100f);
        userDao.updateByName(inuser);
        User touser = new User();
        touser.setUsername(inpeople);
        touser.setMoney(this.getMoney(inpeople)+100f);
        userDao.updateByName(touser);
    }

    @Test
    public void testTransform(){
        this.Tranform("eee","shown",100f);
    }


}
