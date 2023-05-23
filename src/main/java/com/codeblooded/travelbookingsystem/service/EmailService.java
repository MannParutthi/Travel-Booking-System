package com.codeblooded.travelbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private void sendEmailWithTemplate(String recipientEmail, String subject, String htmlContent) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendAccountRegistrationEmail(String recipientEmail) {
        String subject = "Welcome to Concordia Travel Booking System !!";
        String htmlContent = "<html><body><h1>Welcome to the Concordia Travel Booking System!!</h1><p>This is a registration email.</p></body></html>";

        sendEmailWithTemplate(recipientEmail, subject, htmlContent);
    }
}
