package com.nike.douye.service.Impl;

import com.nike.douye.Enum.Code;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.LoginMapper;
import com.nike.douye.service.LoginService;
import com.nike.douye.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public String login(UserDTO user) {
        if(StringUtils.isBlank(user.getUserName())){
            throw new BaseException("请输入用户名或手机号",Code.PARAM_MISSING.getValue());
        }else if(StringUtils.isBlank(user.getPassword())){
            throw new BaseException("请输入密码",Code.PARAM_MISSING.getValue());
        }
        UserDTO userDTO = loginMapper.queryUserByUserNameOrPhone(user.getUserName());
        if(userDTO==null){
            throw new BaseException("用户名不正确", Code.PARAM_ERROR.getValue());
        }else if(!userDTO.getPassword().equals(user.getPassword())){
            throw new BaseException("密码不正确", Code.PARAM_ERROR.getValue());
        }
        String token = JwtUtil.createJWT(1000 * 60, userDTO);
//        loginMapper.insertAccessToken(userDTO.getId(),token);
        return token;
    }



    @Test
    public void test(){

    }
}
