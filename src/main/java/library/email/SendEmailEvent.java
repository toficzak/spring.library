package library.email;

import org.springframework.context.ApplicationEvent;

public class SendEmailEvent extends ApplicationEvent {
  private static final long serialVersionUID = 1L;

  public String email;
  public String subject;
  public String body;

  public SendEmailEvent(Object source, String email, String subject, String body) {
    super(source);
    this.email = email;
    this.subject = subject;
    this.body = body;
  }

}
