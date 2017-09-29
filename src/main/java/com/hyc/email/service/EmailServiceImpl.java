package com.hyc.email.service;

import com.hyc.email.dto.Pair;
import com.hyc.email.exception.RuntimeServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;
//    @Autowired
//    private VelocityEngine velocityEngine;

    @Override
    public void sendSimpleMail(String sendTo, String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        //1.发送人
        message.setFrom(emailConfig.getEmailFrom());

        //2.接受人
        message.setTo(sendTo);

        //3.邮件标题
        message.setSubject(titel);

        //4.邮件内容
        message.setText(content);

        //发送邮件
        mailSender.send(message);
    }

    @Override
    public void sendAttachmentsMail(String sendTo, String titel, String content, String pathame , String attachmentFilename) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText(content);

            FileSystemResource file = new FileSystemResource(new File(pathame));
            helper.addAttachment(attachmentFilename, file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<javafx.util.Pair<String, File>> attachments) {

    }


}
