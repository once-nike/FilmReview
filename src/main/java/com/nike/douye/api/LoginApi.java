package com.nike.douye.api;

import com.nike.douye.Enum.Code;
import com.nike.douye.ValidGroup.ValidGroupB;
import com.nike.douye.ValidGroup.ValidGroupF;
import com.nike.douye.dto.ResponseDTO;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginApi {
    @Autowired
    LoginService loginService;

    @RequestMapping("/userName")
    public ResponseDTO<String> loginByUserName(@Validated(value = ValidGroupF.class)@RequestBody UserDTO user){
        String token;
        if(user!=null){
           token = loginService.loginByUserName(user);
        }else throw new BaseException("缺失参数", Code.PARAM_MISSING.getValue());
        return new ResponseDTO<>(Code.SUCCESS.getValue(),token);
    }
    @RequestMapping("/email")
    public ResponseDTO<String> loginByEmail(@Validated(value = ValidGroupB.class)@RequestBody UserDTO user){
        String token;
        if(user!=null){
           token = loginService.loginByEmail(user);
        }else throw new BaseException("缺失参数", Code.PARAM_MISSING.getValue());
        return new ResponseDTO<>(Code.SUCCESS.getValue(),token);
    }
}
