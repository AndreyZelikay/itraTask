package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender  {

    private JavaMailSender javaMailSender;

    public void send(String EmailTo,String subject, String message){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("");
        mailMessage.setTo(EmailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
