package com.nike.douye.service.Impl;

import com.github.pagehelper.util.StringUtil;
import com.nike.douye.Enum.Code;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.UserMapper;
import com.nike.douye.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserDTO user){
        //用户名
            List<UserDTO> users = userMapper.queryUserByPhonesOrUserName(user.getUserName(),0);
            if(!users.isEmpty()){
                throw new BaseException("此用户名重复",Code.PARAM_REPEATED.getValue());
            }

        //手机
        if(StringUtil.isNotEmpty(user.getPhone())){
            List<UserDTO> usersWithPhone = userMapper.queryUserByPhonesOrUserName(user.getPhone(),0);
            if(!usersWithPhone.isEmpty()){
                throw new BaseException("此手机号重复",Code.PARAM_REPEATED.getValue());
            }
            if(!(user.getPhone().matches("[0-9]+"))&&!(user.getPhone().length()==11)){
                throw new BaseException("手机号格式不正确",Code.PARAM_FORMAT_ERROR.getValue());
            }
        }
        userMapper.insertUser(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(UserDTO user) {
        //用户名
            List<UserDTO> users = userMapper.queryUserByPhonesOrUserName(user.getUserName(),user.getId());
            if(!users.isEmpty()){
                throw new BaseException("此用户名重复",Code.PARAM_REPEATED.getValue());
            }

        //手机
        if(StringUtil.isNotEmpty(user.getPhone())){
            List<UserDTO> usersWithPhone = userMapper.queryUserByPhonesOrUserName(user.getPhone(),user.getId());
            if(!usersWithPhone.isEmpty()){
                throw new BaseException("此手机号重复",Code.PARAM_REPEATED.getValue());
            }
            if(!(user.getPhone().matches("[0-9]+"))&&!(user.getPhone().length()==11)){
                throw new BaseException("手机号格式不正确",Code.PARAM_FORMAT_ERROR.getValue());
            }
        }

        userMapper.updateUser(user);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserDTO queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    @Test
    public void test()  {
            System.out.println(System.currentTimeMillis());
    }
}
