package com.nike.douye.mapper;

import com.nike.douye.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
public interface LoginMapper {
    /**
     * 根据用户名或手机号查询密码
     * @param userNameOrPhone
     * @return
     */
    UserDTO queryUserByUserNameOrPhone(@Param("userNameOrPhone") String userNameOrPhone);

    /**
     * 获取uuid
     * @return
     */
    String queryUuid();

    /**
     * 将token添加入库
     * @param userId
     * @param token
     */
    void insertAccessToken(@Param("userId")Integer userId,@Param("token")String token);

    /**
     * 删除数据库中的token记录
     * @param userId
     * @param token
     */
    void deleteAccessToken(@Param("userId")Integer userId,@Param("token")String token);
}
