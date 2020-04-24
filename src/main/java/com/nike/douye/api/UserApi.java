package com.nike.douye.api;

import com.nike.douye.Enum.Code;
import com.nike.douye.ValidGroup.ValidGroupC;
import com.nike.douye.ValidGroup.ValidGroupD;
import com.nike.douye.ValidGroup.ValidGroupE;
import com.nike.douye.annotation.CheckToken;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.dto.VerificationCodeDto;
import com.nike.douye.service.UserService;
import com.nike.douye.ValidGroup.ValidGroupA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param verificationCodeDto
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDTO<String> addUser(@Validated(value = ValidGroupA.class) @RequestBody @NotNull VerificationCodeDto verificationCodeDto){
            userService.addUser(verificationCodeDto.getUserDTO(),verificationCodeDto.getVerificationCode());
        return new ResponseDTO<>(Code.SUCCESS.getValue(),"注册成功");
    }

    @RequestMapping("/verificationCode")
    public ResponseDTO<String> verificationCode(@RequestParam @Email(message = "email格式不对哦")
                                              @NotEmpty(message = "email不可以为空哦") String email){
            userService.sendVerificationCode(email);
            return new ResponseDTO<>(Code.SUCCESS.getValue(),"验证码已发送至你的邮箱");
    }

    /**
     * 更新用户基本信息
     * @param user
     * @return
     */
    @RequestMapping("/update/information")
    @CheckToken
    public ResponseDTO<String> updateUser(@Validated(value = ValidGroupC.class) @RequestBody UserDTO user){
        if(user!=null){
            userService.updateUser(user);
        }
        return new ResponseDTO<>(Code.SUCCESS.getValue(),"用户信息修改成功");
    }

    /**
     * 查询用户
     * @param
     * @return
     */
    @RequestMapping("/query/information")
    @CheckToken
    public ResponseDTO<UserDTO> queryUser(){
        return new ResponseDTO<>(Code.SUCCESS.getValue(),userService.queryUser());
    }

    @RequestMapping("/update/email")
    @CheckToken
    public ResponseDTO<String> updateEmail(@Validated(value = ValidGroupD.class) @RequestBody VerificationCodeDto verificationCodeDto){

        userService.updateEmail(verificationCodeDto);
        return new ResponseDTO<>( Code.SUCCESS.getValue(),"邮箱更改成功啦");
    }

    @RequestMapping("/update/password")
    @CheckToken
    public ResponseDTO<String> updatePassword(@Validated(value = ValidGroupE.class) @RequestBody VerificationCodeDto verificationCodeDto){
        userService.updatePassword(verificationCodeDto);
        return new ResponseDTO<>( Code.SUCCESS.getValue(),"密码更改成功啦");
    }


}
