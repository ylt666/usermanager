package com.atguigu.usermanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.usermanage.pojo.User;
import com.atguigu.usermanage.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /*@RequestMapping("users")
    public String toUsers(){
        
        return "users";
    }
    @RequestMapping("page/add")
    public String toUserAdd(){
        
        return "user-add";
    }*/
    
    /*@RequestMapping("page/{pageName}")
    public String toPage(@PathVariable("pageName")String pageName){
        
        return pageName;
    }*/
    
    @GetMapping("list")
    @ResponseBody
    public Map<String, Object> queryUserAll(){
        
        return this.userService.queryUserAll();
    }
    
    @PostMapping("save")
    @ResponseBody
    public Map<String, Object> saveUser(@Valid User user, BindingResult result){
        Map<String, Object> map = new HashMap<>();
        
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error->{
                System.out.println("======================" + error.getDefaultMessage());
            });
            map.put("status", 500);
            return map;
        }
        
        
        Boolean b = this.userService.saveUser(user);
        if (b) {
            map.put("status", 200);
        } else {
            map.put("status", 500);
        }
        return map;
    }
    
    @PostMapping("edit")
    @ResponseBody
    public Map<String, Object> editUser(User user){
        Map<String, Object> map = new HashMap<>();
        
        Boolean b = this.userService.editUser(user);
        if (b) {
            map.put("status", 200);
        } else {
            map.put("status", 500);
        }
        return map;
    }
    
    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestParam("ids")List<Long> ids){
        Map<String, Object> map = new HashMap<>();
        
        Boolean b = this.userService.deleteUser(ids);
        if (b) {
            map.put("status", 200);
        } else {
            map.put("status", 500);
        }
        return map;
    }

}
