package tools.Spring.messaging;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

	@Autowired
	private JavaMailSender sender;

	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.to}")
	private String to;
	
	@Value("${mail.subject:}")
	private String subject;
	
	@Value("${mail.text:}")
	private String text;

	private MimeMessageHelper message;
	private boolean messageSet = false;
	
	public void sendMail(MimeMessageHelper message) {
        this.message = message;
        sendMail();
    }

    public void sendMail(){
		sender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException {
				MimeMessageHelper message = messageSet ? getMessage() : new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom(from);
				message.setTo(to);
				message.setSubject(subject);
				message.setText(text, true);
//				message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
//				message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
			}
		});
	}

	public MimeMessageHelper newMessage(){
        return new MimeMessageHelper(sender.createMimeMessage());
    }

	private MimeMessageHelper getMessage(){
		return message;
	}

}
