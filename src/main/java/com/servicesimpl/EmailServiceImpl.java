package com.servicesimpl;

import com.daoapi.OTPDao;
import com.daoapi.UserDao;
import com.entities.OTPMapping;
import com.entities.User;
import com.servicesapi.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * Created by ankur on 13/7/17.
 */

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private UserDao userDao;

    private User user;
    private OTPMapping otpMapping = new OTPMapping();
    private Random rand;

    @Autowired
    private OTPDao otpDao;

    final static String from = "noreply.linksharing@gmail.com ";
    final static String password = "8447323218abc";
    final static String sub = "OTP for Password Recovery";
    //final static String msg = "";

    public boolean sendOTPToUser(String email){
        user =  userDao.findByEmail(email);
        if(user!=null){
            rand = new Random();
            int n = rand.nextInt(89999) + 10001;
            send(user.getEmail(),"Your One Time Password is " + n);
            otpMapping.setEmail(email);
            otpMapping.setOtp(n);
            otpDao.save(otpMapping);
            return true;
        }else {
            return false;
        }
    }

    public static void send(String to,String msg){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}
/*
public class SendMailSSL{
    public static void main(String[] args) {
        //from,password,to,subject,message
        EmailServiceImpl.send("ankur.upadhyay@tothenew.com","8447323218abc","ankur.upadhyay@tothenew.com","OTP for Password Recovery","Your OTP is");
        //change from, password and to
    }
}*/
