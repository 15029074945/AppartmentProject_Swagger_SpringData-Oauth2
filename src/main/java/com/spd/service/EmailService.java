package com.spd.service;

import com.spd.exception.MessagingUserException;
import com.spd.util.EmailUtil;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@Service
public class EmailService {

    private static final String SUBJECT = "Verify Booking";
    private static final String VERIFY = "To confirm the email, click the link: ";

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

        String hostName = null;
        InetAddress inetAddress = null;

        try {
            inetAddress = InetAddress.getLocalHost();
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println(hostName);

        // TODO: get domain with details
        //String remoteAddress = ((WebAuthenticationDetails)(SecurityContextHolder.getContext().getAuthentication().getDetails()))
        //        .getRemoteAddress();
        //return VERIFY + "https://" + remoteAddress + "/verify-email?access_token=" + token;
        // "https://house-hunter.herokuapp.com/verify-email?token="

        return VERIFY + hostName + "/verify-email?token=" + token;
    }
}
