package com.nike.douye.mapper;

import com.nike.douye.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
public interface LoginMapper {
    /**
     * 根据用户名或手机号查询密码
     * @param userName
     * @return
     */
    UserDTO queryUserByUserName(@Param("userName") String userName);
    /**
     * 根据用户名或手机号查询密码
     * @param email
     * @return
     */
    UserDTO queryUserByEmail(@Param("email") String email);
}
