import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendingEmail {

	public static void naverMailSend() {
		String host = "smtp.naver.com"; // ���̹��� ��� ���̹� ����, gmail��� gmail ����
		String user = "yonghoon1999@naver.com"; // �н�����
		String password = "ysj57111";

		// SMTP ���� ������ �����Ѵ�.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("yonghoon1999@naver.com"));

			// ���� ����
			message.setSubject("TEST");

			// ���� ����
			message.setText("KTKO Success!!");

			// send the message
			Transport.send(message);
			System.out.println("Success Message Send");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
