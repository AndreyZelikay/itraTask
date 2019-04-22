package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender  {
    @Autowired
    @Qualifier("getMailSender")
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String EmailTo,String subject, String message){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(EmailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }
}
