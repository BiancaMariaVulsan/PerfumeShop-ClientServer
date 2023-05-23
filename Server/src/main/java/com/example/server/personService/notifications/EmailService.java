package com.example.server.personService.notifications;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Properties;

public class EmailService {

    public static void sendMessage(String to, String subject, String text) throws MessagingException, IOException {
        JavaMailSender emailSender = getJavaMailSender();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("biavulsan@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("biavulsan@gmail.com"); //https://myaccount.google.com/apppasswords?rapt=AEjHL4Oplo21ZWhGFD4aLuD59NsEAhcjrfKxu8XszH7kP3zXRw1RVUKE0_8Oud1SwDOW9Fv7Se_kFmSOCiDtKtSoK5OnP3XNUg
        mailSender.setPassword("gipymbdyrpesedba");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
