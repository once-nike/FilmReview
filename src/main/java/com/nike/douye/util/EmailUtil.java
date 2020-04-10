package com.nike.douye.util;

import com.nike.douye.dto.UserDTO;
import com.nike.douye.entity.MessageWithVerificationCode;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
	public static String sendMessage(String receiveMail)throws Exception{
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties();                    // 参数配置
		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", "smtp.qq.com");   // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getInstance(props);
		// 设置为debug模式, 可以查看详细的发送 log
		session.setDebug(true);

		// 3. 创建一封邮件
		MessageWithVerificationCode messageWithVerificationCode = createMimeMessage(session, "310873705@qq.com", receiveMail);

		MimeMessage mimeMessage = messageWithVerificationCode.getMimeMessage();

		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		transport.connect("310873705@qq.com", "sbzbzstmsdwgcagd");

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

		// 7. 关闭连接
		transport.close();
		return messageWithVerificationCode.getVerificationCode();
	}

	public static MessageWithVerificationCode createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		String verificationCode = createVerificationCode();

		message.setFrom(new InternetAddress(sendMail, "豆叶官方", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "叼毛", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject("验证码", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent("叼毛，这是你得验证码: "+verificationCode+"，有效时间只有一分钟哦", "text/html;charset=UTF-8");
		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return new MessageWithVerificationCode(message,verificationCode);
	}

	// 制造验证码
	public static String createVerificationCode(){
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer verificationCode = new StringBuffer();
		for(int i=0;i<8;i++){
			int random = (int) (Math.random()*chars.length());
			verificationCode.append(chars.charAt(random));
		}
		return new String(verificationCode);
	}
}
