package com.nike.douye.mapper;

import com.nike.douye.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 新建用户
     * @param user
     */
    void insertUser(UserDTO user);

    /**
     * 根据用户名和手机号查询用户
     * @param userName
     * @return
     */
    List<UserDTO> queryUserByUserName(@Param("userName")String userName);

    /**
     * 更新用户
     * @param user
     */
    void updateUserInformation(UserDTO user);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UserDTO queryUserById(@Param("id") Integer id);

    /**
     *
     * @param email
     * @return
     */
    UserDTO queryUserByEmail(@Param("email")String email);

    /**
     * 查询除该id在内的所有用户名中是否存在当前用户名
     * @param id
     * @return
     */
    String queryUserNameByIdAndUserName(@Param("id") Integer id,@Param("userName")String userName);

    /**
     * 更改用户邮件
     * @param userDTO
     */
    void updateUserEmial(@Param("userDTO")UserDTO userDTO);

    /**
     * 根据id修改用户密码
     * @param userDTO
     */
    void updateUserPassword(@Param("userDTO")UserDTO userDTO);
}
