package com.nike.douye.api;

import com.nike.douye.dto.UserDTO;
import com.nike.douye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthy")
public class HealthyTestApi {

    @Autowired
    UserService userService;
    @RequestMapping("/test")
    public String healthyTest(){
        return "成功拉";
    }
    @RequestMapping("/insert")
    public String insertTest(@RequestBody UserDTO user){
        return "插入成功拉";
    }
}
