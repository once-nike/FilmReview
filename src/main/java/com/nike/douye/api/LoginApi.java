package com.nike.douye.api;

import com.nike.douye.Enum.Code;
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

    @RequestMapping("/in")
    public ResponseDTO<String> login(@RequestBody UserDTO user){
        String token;
        if(user!=null){
           token = loginService.login(user);
        }else throw new BaseException("缺失参数", Code.PARAM_MISSING.getValue());
        return new ResponseDTO<>(Code.SUCCESS.getValue(),token);
    }
}
