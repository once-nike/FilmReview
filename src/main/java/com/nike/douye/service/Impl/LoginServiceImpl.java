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
    public String loginByUserName(UserDTO user) {
        if(StringUtils.isBlank(user.getUserName())){
            throw new BaseException("请输入用户名",Code.PARAM_MISSING.getValue());
        }else if(StringUtils.isBlank(user.getPassword())){
            throw new BaseException("请输入密码",Code.PARAM_MISSING.getValue());
        }
        UserDTO userDTO = loginMapper.queryUserByUserName(user.getUserName());
        if(userDTO==null){
            throw new BaseException("用户名不正确", Code.PARAM_ERROR.getValue());
        }else if(!userDTO.getPassword().equals(user.getPassword())){
            throw new BaseException("密码不正确", Code.PARAM_ERROR.getValue());
        }
        String token = JwtUtil.createJWT(1000 * 60, userDTO);
        return token;
    }

    @Override
    public String loginByEmail(UserDTO user) {
        if(StringUtils.isBlank(user.getEmail())){
            throw new BaseException("请输入email",Code.PARAM_MISSING.getValue());
        }else if(StringUtils.isBlank(user.getPassword())){
            throw new BaseException("请输入密码",Code.PARAM_MISSING.getValue());
        }
        UserDTO userDTO = loginMapper.queryUserByEmail(user.getEmail());
        if(userDTO==null){
            throw new BaseException("email输入有误", Code.PARAM_ERROR.getValue());
        }else if(!userDTO.getPassword().equals(user.getPassword())){
            throw new BaseException("密码不正确", Code.PARAM_ERROR.getValue());
        }
        String token = JwtUtil.createJWT(1000 * 60, userDTO);
        return token;
    }


    @Test
    public void test(){

    }

}
