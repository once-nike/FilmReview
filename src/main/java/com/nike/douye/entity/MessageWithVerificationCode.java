package com.nike.douye.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.mail.internet.MimeMessage;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MessageWithVerificationCode {
	MimeMessage mimeMessage;
	String verificationCode;
}
