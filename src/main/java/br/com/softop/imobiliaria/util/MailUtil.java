package br.com.softop.imobiliaria.util;

import br.com.softop.imobiliaria.util.exception.BusinessException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author danilo
 */
public class MailUtil {

    public static void sendMail(String[] emailsDest, String nomeDest, String emailRemet, String senhaEmail, String nomeRemet, String assunto, String corpo) throws Exception{
        try {
            //Passo 1 - Configuar email
            Properties props = System.getProperties();
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.fallback", "true");

            //Passo 2 - recuperar sessao do email
            Session mailSession = Session.getDefaultInstance(props, null);
            MimeMessage mailMessage = new MimeMessage(mailSession);
            mailMessage.setFrom(new InternetAddress(emailRemet, nomeRemet));
            Address[] addresses = new Address[emailsDest.length];
            for (int i = 0; i < emailsDest.length; i++) {
                addresses[i] = new InternetAddress(emailsDest[i].toLowerCase().trim());
            }
            mailMessage.addRecipients(Message.RecipientType.TO, addresses);
            mailMessage.setSubject(assunto);
            mailMessage.setContent(corpo, "text/html");

            //Passo 3 - Pegar sessao enviar email
            Transport transport = mailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", emailRemet, senhaEmail);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (UnsupportedEncodingException ex) {
            throw new Exception("Erro ao enviar o email. Desculpe, mas parece que estamos com alguns problemas!");
        } catch (MessagingException ex) {
            throw new Exception("Erro ao enviar o email. Desculpe, mas parece que estamos com alguns problemas!");
        }
    }

}
