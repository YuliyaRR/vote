package groupwork.service;

import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import groupwork.service.api.IGenreService;
import groupwork.service.api.ISendMailService;
import groupwork.service.api.ISingerService;

import javax.mail.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.*;

public class SendMailService implements ISendMailService {
    private final String WORK_EMAIL_PROPERTY_NAME  = "mail.box";
    private final String PASSWORD_WORK_EMAIL_PROPERTY_NAME = "mail.password";
    private Properties properties;
    private ISingerService singerService;
    private IGenreService genreService;

    public SendMailService(Properties properties, ISingerService singerService, IGenreService genreService) {
        this.properties = properties;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    public void send(SavedVoiceDTO savedVoiceDTO) {
        String email = savedVoiceDTO.getVoice().getEmail();
        String infoVoice = createInfoVoice(savedVoiceDTO);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty(WORK_EMAIL_PROPERTY_NAME),
                        properties.getProperty(PASSWORD_WORK_EMAIL_PROPERTY_NAME));
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new SendRunnable(session, infoVoice, email));

    }

    private String createInfoVoice(SavedVoiceDTO savedVoiceDTO){
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        VoiceDTO voice = savedVoiceDTO.getVoice();

        int singerID = voice.getSinger();
        int[] genreID = voice.getGenre();
        String email = voice.getEmail();
        String message = voice.getMessage();
        LocalDateTime creationTime = savedVoiceDTO.getCreationTime();

        String singer = singerService.getSinger(singerID).getName();

        builder.append("Ваш голос: исполнитель - ").append(singer)
                .append(", жанры - ");

        for (int genre : genreID) {
            builder.append(genreService.getGenre(genre).getName()).append(", ");
        }

        builder.append("информация о себе - ").append(message)
                .append(", email - ").append(email)
                .append(", дата и время голосования - ").append(dtf.format(creationTime));


        return builder.toString();
    }

}
