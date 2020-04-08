package com.nike.douye.service;


import com.nike.douye.dto.UserDTO;

public interface UserService {

    /**
     * 添加用户
     * @param user
     */
    void addUser(UserDTO user);
    /**
     * 修改用户
     * @param user
     */
    void updateUser(UserDTO user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserDTO queryUserById(Integer id);
}
