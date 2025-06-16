package com.trong.commonservice.services;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private Configuration config;


    /**
     * Sends an email with optional HTML content and attachment.
     *
     * @param to         The recipient's email address.
     * @param subject    The subject of the email.
     * @param text       The body of the email, can be HTML or plain text.
     * @param isHtml     Whether the email body is HTML or plain text.
     * @param attachment An optional file attachment, can be null.
     */
    public void sendEmail(String to, String subject, String text,boolean isHtml, File attachment) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);

            //Add attachment if provided
            if (attachment != null) {
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(file.getFilename(), file); // Add attachment
            }


            javaMailSender.send(message);
            log.info("Email sent successfully to {}", to);

        }catch (MessagingException e) {
            log.error("Failed to send email", e);
        }
    }


    /**
     * Sends an email with optional HTML content and attachment.
     *
     * @param to         The recipient's email address.
     * @param subject    The subject of the email.
     * @param templateName       The body of the email, can be HTML or plain text.
     * @param placeHolders     Whether the email body is HTML or plain text.
     * @param attachment An optional file attachment, can be null.
     */
    public void sendEmailWithTeamplate(String to, String subject, String templateName, Map<String, Object> placeHolders, File attachment) {
        try{
            Template template = config.getTemplate(templateName);
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, placeHolders);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            //Add attachment if provided
            if (attachment != null) {
                FileSystemResource file = new FileSystemResource(attachment);
                helper.addAttachment(file.getFilename(), file); // Add attachment
            }
            javaMailSender.send(message);
            log.info("Email sent successfully to {}", to);
        }catch(MessagingException | IOException | TemplateException e){
            log.error("Failed to send email", e);
        }

    }
}
