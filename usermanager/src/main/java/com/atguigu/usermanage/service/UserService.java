package com.atguigu.usermanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.usermanage.mapper.UserMapper;
import com.atguigu.usermanage.pojo.User;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> queryUserAll(){
        Map<String, Object> map = new HashMap<>();
        
        // 查询总条数
        Long count = this.userMapper.queryCount();
        map.put("total", count);
        
        // 查询所有记录
        List<User> users = this.userMapper.queryUserAll();
        map.put("rows", users);
        
        return map;
    }
    
    public List<User> queryUser(){
        
        return this.userMapper.queryUserAll();
    }
    
    public void saveUsers(User user1, User user2){
        this.userMapper.insertUser(user1);
        ///int i = 1/0;
        this.userMapper.insertUser(user2);
    }

    public Boolean saveUser(User user) {
        
        return this.userMapper.insertUser(user) == 1;
    }

    public Boolean editUser(User user) {
        
        return this.userMapper.editUser(user) == 1;
    }

    public Boolean deleteUser(List<Long> ids) {

        return this.userMapper.deleteUser(ids) > 0;
    }

    public User queryUserById(Long id) {
        return this.userMapper.queryUserById(id);
    }

    public Boolean deleteUserById(Long id) {
        return this.userMapper.deleteUserById(id);
    }
}
