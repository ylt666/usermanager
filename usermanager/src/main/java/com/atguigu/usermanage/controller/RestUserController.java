package com.atguigu.usermanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.usermanage.pojo.User;
import com.atguigu.usermanage.service.UserService;

@Controller
@RequestMapping("rest/user")
public class RestUserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    //@ResponseBody
    public ResponseEntity<User> queryUserById(@PathVariable("id")Long id){
        
        if (id == null || id.longValue() <= 0) {
            // 如果参数不合法，响应400
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            //return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().build();
        }
        
        User user = this.userService.queryUserById(id);
        
        if (user == null) {
            // 查询结果为null，响应404
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        // 查询成功，响应200
        //return ResponseEntity.status(HttpStatus.OK).body(user);
        //return new ResponseEntity<User>(user, HttpStatus.OK);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> queryUserAll(){
        
        List<User> users = this.userService.queryUser();
        if (CollectionUtils.isEmpty(users)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users); 
    }
    
    @PostMapping
    public ResponseEntity<Void> saveUser(User user){
        
        if (user == null || StringUtils.isEmpty(user.getUserName())) {
            return ResponseEntity.badRequest().build();
        }
        Boolean b = this.userService.saveUser(user);
        if (!b) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @PutMapping
    public ResponseEntity<Void> updateUser(User user){
        
        if (user == null || user.getId() == null || user.getId().longValue() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Boolean b = this.userService.editUser(user);
        if (!b) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam("ids")List<Long> ids){
        
        if (ids == null || ids.size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Boolean b = this.userService.deleteUser(ids);
        if (!b) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
