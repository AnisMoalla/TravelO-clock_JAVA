/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import javax.mail.Transport;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author LENOVO
 */
public class SendMail {
     public static void sendMail(String recepient, String htmlCode) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "projetpidev992@gmail.com";
        //Your gmail password
        String password = "ozxcgepevofquhfb";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        MimeMessage message = new MimeMessage(session);

        //Send mail

        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("About Rating");

            message.setContent(htmlCode, "text/html");
            Transport.send(message);
            System.out.println("Message sent successfully");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
