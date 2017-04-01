package com.spd.service;

import com.spd.exception.MessagingUserException;
import com.spd.util.EmailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    @Value("mail.front.host")
    private String frontName;

    private static final String VERIFY_EMAIL_TOKEN = "/verify-email?token=";
    private static final String TO_CONFIRM_THE_EMAIL_CLICK_THE_LINK = "To confirm the email, click the link: ";

    private static final String SUBJECT = "Verify Booking";

    public void sendMail(String to, String body) {

        Properties props = EmailUtil.getProperties();
        Session session = EmailUtil.getSession();

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty(EmailUtil.MAIL_SMTP_USERNAME)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(SUBJECT);
            message.setText(body);

            Transport.send(message);

        } catch (Exception e) {
            throw new MessagingUserException("No send email");
        }

    }

    public String createLink(String token) {
        return TO_CONFIRM_THE_EMAIL_CLICK_THE_LINK + frontName + VERIFY_EMAIL_TOKEN + token;
    }
}
