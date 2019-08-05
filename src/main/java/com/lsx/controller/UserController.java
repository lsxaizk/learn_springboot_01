package com.lsx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsx.model.User;
import com.lsx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @PostMapping("/insert")
    public User InsertUser(@RequestParam(value = "age") Integer age, @RequestParam(value = "password") String password,
                           @RequestParam(value = "userName") String userName){
        User user=new User();
        user.setAge(age);
        user.setPassword(password);
        user.setUserName(userName);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public String DeleteByid(@PathVariable(value = "id")Integer id){
        userRepository.deleteById(id);
        return "已删除id="+id+"的数据";
    }
    @PutMapping("/update/{id}")
    public User UpdateUserById(@PathVariable(value = "id")Integer id,
                               @RequestParam(value = "age",required = false) Integer age,
                               @RequestParam(value = "password",required = false) String password,
                               @RequestParam(value = "userName",required = false) String userName) {
        User user=new User();
        user.setId(id);
        user.setAge(age);
        user.setPassword(password);
        user.setUserName(userName);
        userRepository.saveAndFlush(user);
        return user;
    }

    @GetMapping("/findUserByAge/{age}")
    public List<User> findUserByAge(@PathVariable("age")Integer age){
        List<User> user = userRepository.findByAge(age);
        return user;
    }

    /***
     * 测试redis取值与存值
     *
     * */
    @RequestMapping("/redisTest")
    public String redisTest() throws JsonProcessingException {
        String msg="";
        ObjectMapper objectMapper=new ObjectMapper();

        String Json=redisTemplate.boundValueOps("BootRedis").get();

        if (Json!=null){
            msg=("从redis中取值"+Json);
        }
        else{
            String Json1=objectMapper.writeValueAsString(userRepository.findAll());
            redisTemplate.boundValueOps("BootRedis").set(Json1);
            msg=("从mysql获取值"+Json1);
        }
        return msg;
    }

}
