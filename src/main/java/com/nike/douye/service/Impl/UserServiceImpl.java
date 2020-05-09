package com.nike.douye.service.Impl;

import com.auth0.jwt.JWT;
import com.github.pagehelper.util.StringUtil;
import com.nike.douye.Enum.Code;
import com.nike.douye.dto.UserDTO;
import com.nike.douye.dto.VerificationCodeDto;
import com.nike.douye.exception.BaseException;
import com.nike.douye.mapper.UserMapper;
import com.nike.douye.service.UserService;
import com.nike.douye.util.EmailUtil;
import com.nike.douye.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void addUser(UserDTO user,String verificationCode){
        String email = user.getEmail();
        //用户名
            List<UserDTO> users = userMapper.queryUserByUserName(user.getUserName());
            if(!users.isEmpty()){
                throw new BaseException("该用户名已被注册！",Code.PARAM_ALREAD_EXIST.getValue());
            }

        //手机
        if(StringUtil.isNotEmpty(user.getPhone())){
            if(!(user.getPhone().matches("[0-9]+"))&&!(user.getPhone().length()==11)){
                throw new BaseException("手机号格式不正确",Code.PARAM_FORMAT_ERROR.getValue());
            }
        }
        UserDTO userDTO = userMapper.queryUserByEmail(email);
        if(userDTO != null){
            throw new BaseException("该邮箱已被注册",Code.PARAM_ALREAD_EXIST.getValue());
        }
        //验证邮箱
        validation(email,verificationCode);

        userMapper.insertUser(user);
        redisUtil.deleteKey(email);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(UserDTO user) {
        //取userId
        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
        //用户名
        String userName = userMapper.queryUserNameByIdAndUserName(userId, user.getUserName());
        if(userName != null){
            throw new BaseException("该用户名已被注册！",Code.PARAM_ALREAD_EXIST.getValue());
        }

        //手机
        if(StringUtil.isNotEmpty(user.getPhone())){
            if(!(user.getPhone().matches("[0-9]+"))&&!(user.getPhone().length()==11)){
                throw new BaseException("手机号格式不正确",Code.PARAM_FORMAT_ERROR.getValue());
            }
        }

        user.setId(userId);
        userMapper.updateUserInformation(user);
    }

    // 改邮箱
    @Override
    public void updateEmail(VerificationCodeDto verificationCodeDto) {
        //取userId
        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
        verificationCodeDto.getUserDTO().setId(userId);


         String email = verificationCodeDto.getUserDTO().getEmail();
         String verificationCode = verificationCodeDto.getVerificationCode();
        UserDTO userDTO = userMapper.queryUserByEmail(email);
        if(userDTO != null){
            throw new BaseException("该邮箱已被注册",Code.PARAM_ALREAD_EXIST.getValue());
        }
         validation(email,verificationCode);
        userMapper.updateUserEmial(verificationCodeDto.getUserDTO());
    }

    // 改密码
    @Override
    public void updatePassword(VerificationCodeDto verificationCodeDto) {
        //取userId
        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
        verificationCodeDto.getUserDTO().setId(userId);


        String email = verificationCodeDto.getUserDTO().getEmail();
        String verificationCode = verificationCodeDto.getVerificationCode();
        validation(email,verificationCode);
        userMapper.updateUserPassword(verificationCodeDto.getUserDTO());
    }

    /**
     * 根据id查询用户信息
     * @param
     * @return
     */
    @Override
    public UserDTO queryUserById(Integer userId) {
        return userMapper.queryUserById(userId);
    }

    /**
     * 发送邮件验证码
     * @param email
     */
    @Override
    public void sendVerificationCode(String email) {
        try {
            String verificationCode = EmailUtil.sendMessage(email);
            redisUtil.setStringValue(email,verificationCode);
            redisUtil.expireKeyAt(email,new Date(System.currentTimeMillis()+1000*60));
        }catch (Exception e){
            throw new BaseException("验证码发送出错了");
        }
    }

    @Override
    public UserDTO queryUser() {
        //取userId
        String token = request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getClaim("id").asString());
        return userMapper.queryUserById(userId);
    }


    public  void validation(String email,String erificationCode){

        if(!redisUtil.existsKey(email)){
            throw new BaseException("验证码已过期！",Code.PARAM_ERROR.getValue());
        }else if(!erificationCode.equals(redisUtil.getStringValue(email))){
            throw new BaseException("验证码不正确，给爷爬",Code.PARAM_ERROR.getValue());
        }
    }

    @Test
    public void test()  {
            System.out.println(System.currentTimeMillis());
    }
}
