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
     * @param phoneOrUserName
     * @return
     */
    List<UserDTO> queryUserByPhonesOrUserName(@Param("phoneOrUserName")String phoneOrUserName,@Param("id")Integer id);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(UserDTO user);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UserDTO queryUserById(@Param("id") Integer id);
}
