package com.spd.service;

import com.spd.util.EmailUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {

    private static final String SUBJECT = "Verify Booking";
    private static final String VERIFY = "To confirm the email, click the link: ";

    public void sendMail(String to, String body) throws MessagingException {

        Properties props = EmailUtil.getProperties();
        Session session = EmailUtil.getSession();

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty(EmailUtil.MAIL_SMTP_USERNAME + "@@@@@")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(SUBJECT);
            message.setText(body);

            Transport.send(message);

        } catch (Exception e) {
            throw new MessagingException();
        }

    }

    public String createLink(String token) {

        // TODO: get domain with details
        //String remoteAddress = ((WebAuthenticationDetails)(SecurityContextHolder.getContext().getAuthentication().getDetails()))
        //        .getRemoteAddress();
        //return VERIFY + "https://" + remoteAddress + "/verify-email?access_token=" + token;

        return VERIFY + "https://house-hunter.herokuapp.com/verify-email?token=" + token;
    }
}
