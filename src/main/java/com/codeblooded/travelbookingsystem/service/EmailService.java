package com.codeblooded.travelbookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.io.StringWriter;

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

    public void sendAccountRegistrationEmail(String recipientEmail) {
        String templateFilePath = "Email-Templates/NewUserRegistrationTemplate.html";
        String subject = "Concordia Travel Booking System: Welcome to Concordia Travel Booking System";
        AccountRegistrationTemplateData accountRegistrationTemplateData = new AccountRegistrationTemplateData(recipientEmail);

        try {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile(templateFilePath);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, accountRegistrationTemplateData).flush();

            String htmlContent = writer.toString();
            sendEmailWithTemplate(recipientEmail, subject, htmlContent);
        } catch (IOException e) {
            System.out.println("----- Error in reading HTML Template: " +  templateFilePath +"-----");
        }
    }

    public void sendBookingConfirmationEmail(String emailAddress, int customerID, int bookingID, int paymentID, int travelPackageID, String departureDate, String bookingStatus) {
        String templateFilePath = "Email-Templates/BookingConfirmationTemplate.html";
        String subject = "Concordia Travel Booking System: Booking Confirmation";
        BookingConfirmationTemplateData bookingConfirmationTemplateData = new BookingConfirmationTemplateData(customerID, bookingID, paymentID, travelPackageID, departureDate, bookingStatus);

        try {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile(templateFilePath);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, bookingConfirmationTemplateData).flush();

            String htmlContent = writer.toString();
            sendEmailWithTemplate(emailAddress, subject, htmlContent);
        } catch (IOException e) {
            System.out.println("----- Error in reading HTML Template: " +  templateFilePath +"-----");
        }
    }

    public void sendBookingUpdateEmail(String emailAddress, int customerID, int bookingID, int travelPackageID, String departureDate, String bookingStatus) {
        String templateFilePath = "Email-Templates/BookingUpdateTemplate.html";
        String subject = "Concordia Travel Booking System: Booking Confirmation";
        BookingUpdateTemplateData bookingUpdateTemplateData = new BookingUpdateTemplateData(customerID, bookingID, travelPackageID, departureDate, bookingStatus);

        try {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile(templateFilePath);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, bookingUpdateTemplateData).flush();

            String htmlContent = writer.toString();
            sendEmailWithTemplate(emailAddress, subject, htmlContent);
        } catch (IOException e) {
            System.out.println("----- Error in reading HTML Template: " +  templateFilePath +"-----");
        }
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

    private static class BookingConfirmationTemplateData {
        private final int customerID;
        private final int bookingID;
        private final int paymentID;
        private final int travelPackageID;
        private final String departureDate;
        private final String bookingStatus;


        private BookingConfirmationTemplateData(int customerID, int bookingID, int paymentID, int travelPackageID, String departureDate, String bookingStatus) {
            this.customerID = customerID;
            this.bookingID = bookingID;
            this.paymentID = paymentID;
            this.travelPackageID = travelPackageID;
            this.departureDate = departureDate;
            this.bookingStatus = bookingStatus;
        }

        public int getCustomerID() {
            return customerID;
        }

        public int getBookingID() {
            return bookingID;
        }

        public int getPaymentID() {
            return paymentID;
        }

        public int getTravelPackageID() {
            return travelPackageID;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public String getBookingStatus() {
            return bookingStatus;
        }
    }

    private static class BookingUpdateTemplateData {
        private final int customerID;
        private final int bookingID;
        private final int travelPackageID;
        private final String departureDate;

        private final String bookingStatus;

        private BookingUpdateTemplateData(int customerID, int bookingID, int travelPackageID, String departureDate, String bookingStatus) {
            this.customerID = customerID;
            this.bookingID = bookingID;
            this.travelPackageID = travelPackageID;
            this.departureDate = departureDate;
            this.bookingStatus = bookingStatus;
        }

        public int getCustomerID() {
            return customerID;
        }

        public int getBookingID() {
            return bookingID;
        }

        public int getTravelPackageID() {
            return travelPackageID;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public String getBookingStatus() {
            return bookingStatus;
        }
    }
}
