package library.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

  private JavaMailSender emailSender;
  private String from;

  public EmailSender(
      JavaMailSender emailSender,
      @Value("${spring.mail.username}") String from) {
    super();
    this.emailSender = emailSender;
    this.from = from;
  }

  @Async
  @EventListener
  public void sendSimpleMessage(SendEmailEvent event) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(event.email);
    message.setSubject(event.subject);
    message.setText(event.body);
    emailSender.send(message);
  }
}
