package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.math3.random.RandomDataGenerator;

/**
 * Created by vigour on 2014-8-12.
 */
public class TestEmail {
    public static void main(String[] args) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("192.168.150.11");
        email.setSmtpPort(25);
        // email.setAuthenticator(new DefaultAuthenticator("username", "password"));
        // email.setSSLOnConnect(false);
        email.setFrom("user@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("jiangfuqiang@unitedmne.com");
        email.send();

        RandomDataGenerator gen = new RandomDataGenerator();
    }
}
