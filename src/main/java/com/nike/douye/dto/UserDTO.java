package com.nike.douye.dto;

import com.nike.douye.ValidGroup.ValidGroupA;
import com.nike.douye.ValidGroup.ValidGroupB;
import com.nike.douye.ValidGroup.ValidGroupC;
import com.nike.douye.ValidGroup.ValidGroupD;
import com.nike.douye.ValidGroup.ValidGroupE;
import com.nike.douye.ValidGroup.ValidGroupF;
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
    @Size(max = 16,min = 4,message = "用户名最长是16，最短是4",groups = {ValidGroupA.class, ValidGroupC.class, ValidGroupF.class})
    @NotNull(message = "用户名不能为空哦",groups ={ValidGroupA.class, ValidGroupC.class, ValidGroupF.class})
    @Pattern(regexp = "[^\\u4e00-\\u9fa5]+",message = "不可以出现汉字哦",groups = {ValidGroupA.class, ValidGroupF.class})
    String userName;
    /**
     * 密码
     */
    @Size(max = 16,min = 4,message = "密码最长是16，最短是4",groups = {ValidGroupA.class, ValidGroupE.class, ValidGroupF.class, ValidGroupB.class})
    @Pattern(regexp = "[^\\u4e00-\\u9fa5]+",message = "不可以出现汉字哦",groups = {ValidGroupA.class, ValidGroupE.class, ValidGroupF.class, ValidGroupB.class})
    @NotNull(message = "密码不能为空哦",groups = {ValidGroupA.class, ValidGroupE.class, ValidGroupF.class, ValidGroupB.class})
    String password;
    /**
     * 邮箱
     */
    @NotEmpty(message = "请输入email",groups = {ValidGroupA.class, ValidGroupD.class, ValidGroupE.class, ValidGroupB.class})
    @Email(message = "email格式不对哦",groups = {ValidGroupA.class, ValidGroupD.class, ValidGroupE.class, ValidGroupB.class})
    String email;

    String phone;

    @Length(max = 200,message = "地址只能输入200字以内的任意字符",groups ={ValidGroupA.class, ValidGroupC.class})
    String address;
    String isAdmin;

    public UserDTO(String userName, String email, String phone,  String address, String isAdmin) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public UserDTO(String userName, String email, String phone, String address) {
        this.userName = userName;
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

    public UserDTO(Integer id, String userName, String phone, String address) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
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
