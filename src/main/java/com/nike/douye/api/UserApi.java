package com.nike.douye.api;

import com.nike.douye.annotation.Authorization;
import com.nike.douye.annotation.CheckToken;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public ResponseDTO<String> addUser(@RequestBody UserDTO user){
        if(user!=null){
            userService.addUser(user);
        }
        return new ResponseDTO<>("注册成功");
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Authorization
    @RequestMapping("/update")
    public ResponseDTO<String> updateUser(@RequestBody UserDTO user){
        if(user!=null){
            userService.updateUser(user);
        }
        return new ResponseDTO<>("用户信息修改成功");
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Authorization
    @RequestMapping("/query")
    @CheckToken
    public ResponseDTO<UserDTO> queryUser(@RequestParam Integer id){
        return new ResponseDTO<>(userService.queryUserById(id));
    }

}
