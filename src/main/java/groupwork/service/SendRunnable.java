package groupwork.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendRunnable implements Runnable {
    private final String WORK_EMAIL_PROPERTY_NAME  = "mail.box";
    private String info;
    private Session session;

    private String email;

    public SendRunnable(Session session, String info, String email) {
        this.session = session;
        this.info = info;
        this.email = email;
    }

    @Override
    public void run() {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(session.getProperty(WORK_EMAIL_PROPERTY_NAME)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your voice");
            message.setText(info);
            Transport.send(message);

           //??запись в бд что отправка прошла корректно

        } catch (MessagingException e) {
            //запись в бд о провале операции, необходим повтор
            throw new RuntimeException("ошибка отправки почты", e);

        }
    }

}
