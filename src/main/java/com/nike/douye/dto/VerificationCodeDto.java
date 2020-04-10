package com.nike.douye.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  VerificationCodeDto {

	@Valid
	UserDTO userDTO;
	@NotBlank(message = "验证码不能为空")
	String verificationCode;
}
