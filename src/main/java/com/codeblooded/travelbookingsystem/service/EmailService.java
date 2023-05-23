package com.codeblooded.travelbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.util.StreamUtils;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

// For Email templating
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;


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

    private String renderHtmlTemplateFromFile(String templateFilePath, AccountRegistrationTemplateData templateData) {
        try {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile(templateFilePath);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, templateData).flush();

            return writer.toString();
        } catch (IOException e) {
            System.out.println("----- Error in reading HTML Template: " +  templateFilePath +"-----");
            return null;
        }
    }

    public void sendAccountRegistrationEmail(String recipientEmail) {
        String subject = "Concordia Travel Booking System: Welcome to Concordia Travel Booking System";
        AccountRegistrationTemplateData accountRegistrationTemplateData = new AccountRegistrationTemplateData(recipientEmail);
        String htmlContent = renderHtmlTemplateFromFile("Email-Templates/NewUserRegistrationTemplate.html", accountRegistrationTemplateData);

        sendEmailWithTemplate(recipientEmail, subject, htmlContent);
    }

    private static class AccountRegistrationTemplateData {
        private final String recipientEmailAddress;

        public AccountRegistrationTemplateData(String recipientName) {
            this.recipientEmailAddress = recipientName;
        }

        public String getRecipientEmailAddress() {
            return recipientEmailAddress;
        }
    }
}
