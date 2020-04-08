package com.nike.douye.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
       Integer id;
    /**
     * 用户名
     */
    @Max(value = 16, message = "userName最长为16呦")
    @NotNull(message = "userName不能为空")
    String userName;
    /**
     * 密码
     */
    @Size(max = 16,min = 4,message = "password最长是16，最短是4")
    @Pattern(regexp = "[^\\u4e00-\\u9fa5]+")
    @NotNull(message = "userName不能为空")
    String password;
    /**
     * 邮箱
     */
    @Email(message = "email格式不对哦")
    String email;

    String phone;

    @Length(max = 200,message = "地址只能输入200字以内的任意字符")
    String address;
    String isAdmin;

    public UserDTO(String userName, String password, String email, String phone, String address) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public UserDTO(String userName, String password, String email, String phone, String address, String isAdmin) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }
}
