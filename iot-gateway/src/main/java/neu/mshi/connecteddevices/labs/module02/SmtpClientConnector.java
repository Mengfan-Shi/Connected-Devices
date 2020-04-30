package neu.mshi.connecteddevices.labs.module02;

import java.sql.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Because I am not able to implement professor's guide method, I have to create a SmtpClientConnector class on my own :(
 * The email account info is attached in the code
 */

public class SmtpClientConnector {
	private static final String SMTPHost = "smtp.mail.yahoo.com";
	private static final String SendEmailAccount = "shi.men@yahoo.com";
	private static final String receiveMailAccount = "shi.men@husky.neu.edu";
	private static final String Password = "shengdanjie";
	private static final String smtpPort = "465";
	
/**
 * This method create sending mail session, through specific smtp port and host, after the email is sent, session closed. 
 * @param s: the formatted email context
 */
	
	public static void TestEmail(String s) throws Exception {
		Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SMTPHost);  
        props.setProperty("mail.smtp.auth", "true");            
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = createMimeMessage(session, SendEmailAccount, receiveMailAccount,s);
        Transport t = session.getTransport();
        t.connect(SendEmailAccount, Password);
        t.sendMessage(message, message.getAllRecipients());
        t.close();
	}
	
	/**
	 * @param session: each email is to create a new session
	 * @param sendMail: the email address which sends the alert message
	 * @param receiveMail: the target address
	 * This method generate message created session.
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String s) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "Temperature_Sensor", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "JM", "UTF-8"));
        message.setSubject("Temperature Alert", "UTF-8");
        message.setContent(s, "text/html;charset=UTF-8");
        message.setSentDate(new Date(0));
        message.saveChanges();
        return message;
	}
}
