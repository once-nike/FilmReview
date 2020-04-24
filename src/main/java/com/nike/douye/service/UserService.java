package com.nike.douye.service;


import com.nike.douye.dto.UserDTO;
import com.nike.douye.dto.VerificationCodeDto;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    /**
     * 添加用户
     * @param user
     */
    void addUser(UserDTO user,String verificationCode);
    /**
     * 修改用户
     * @param user
     */
    void updateUser(UserDTO user);

    /**
     * 修改邮箱
     * @param verificationCodeDto
     */
    void updateEmail(VerificationCodeDto verificationCodeDto);

    /**
     * 修改密码
     * @param verificationCodeDto
     */
    void updatePassword(VerificationCodeDto verificationCodeDto);

    /**
     * 根据id查询用户
     * @param
     * @return
     */
    UserDTO queryUserById(Integer userId);

    /**
     * email发送验证码
     * @param email
     */
    void sendVerificationCode(String email);

    /**
     * 查询用户
     * @return
     */
    UserDTO queryUser();
}
