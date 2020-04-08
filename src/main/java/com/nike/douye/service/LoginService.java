package com.nike.douye.service;

import com.nike.douye.dto.UserDTO;

public interface LoginService {
    /**
     * 登录
     * @param user
     * @return
     */
    String login(UserDTO user);
}
