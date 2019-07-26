package com.atguigu.usermanage.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.usermanage.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-tx.xml",
        "classpath:spring/applicationContext-mybatis.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUsers() {
        
        User user1 = new User();
        user1.setUserName("all world");
        user1.setName("全世界");
        user1.setPassword("123456");
        User user2 = new User();
        user2.setUserName("libingbing");
        user2.setName("李冰冰");
        user2.setPassword("123456");
        this.userService.saveUsers(user1 , user2);
    }

}
