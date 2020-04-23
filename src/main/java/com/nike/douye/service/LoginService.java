package com.nike.douye.service;

import com.nike.douye.dto.UserDTO;

public interface LoginService {
    /**
     * 用户名登录
     * @param user
     * @return
     */
    String loginByUserName(UserDTO user);
    /**
     * email登录
     * @param user
     * @return
     */
    String loginByEmail(UserDTO user);
}
