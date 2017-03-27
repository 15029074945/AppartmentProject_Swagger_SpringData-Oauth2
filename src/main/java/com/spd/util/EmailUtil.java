package com.spd.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailUtil {

    public static final String MAIL_SMTP_USERNAME = "mail.smtp.username";

    private static Session session = null;
    private static Properties properties = null;
    
    public static Session getSession() {
        if (session != null) {
            return session;
        }
        else {
            createSession();
            return session;
        }
    }
    
    public static Properties getProperties() {
        if (properties != null) {
            return properties;
        }
        else {
            createProperties();
            return properties;
        }
    }

    private static void createProperties() {
        properties = new Properties();
        properties.put(MAIL_SMTP_USERNAME, "zewsoftware@gmail.com");
        properties.put("mail.smtp.password", "qwerty94");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    private static void  createSession() {
        Properties props = getProperties();
        session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                props.getProperty("mail.smtp.username"),
                                props.getProperty("mail.smtp.password")
                        );
                    }
                });
    }

}
