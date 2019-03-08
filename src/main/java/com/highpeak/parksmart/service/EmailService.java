package com.highpeak.parksmart.service;

import com.highpeak.parksmart.pojo.MailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private Environment environment;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int smtpPort;

    public void sendEmail(MailBean mailBean)
    {

        logger.info("sending mail");

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try
        {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailBean.getTo()));
            message.setSubject(mailBean.getSubject());
            message.setText(mailBean.getContent());

            Transport.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

