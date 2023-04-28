package groupwork.web.controllers;

import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;
import groupwork.service.api.IVotesService;
import groupwork.service.fabrics.VoteServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserAnswerServlet", urlPatterns = "/answer")
public class UserAnswerServlet extends HttpServlet {
    private final IVotesService service;

    private final String SINGER_PARAM_NAME = "singer";
    private final String GENRE_PARAM_NAME = "genre";
    private final String ABOUT_USER_PARAM_NAME = "about_user";
    private final String EMAIL_USER_PARAM_NAME = "email";

    public UserAnswerServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override //temporarily
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        List<SavedVoiceDTO> listVoice = service.get();
        listVoice.forEach(voice -> writer.write("<p>" + voice + "</p>"));
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        try {
            Map<String, String[]> parameterMap = req.getParameterMap();

            String[] singers = parameterMap.get(SINGER_PARAM_NAME);

            if (singers == null || singers.length > 1) {
                throw new IllegalArgumentException("Choose one singer");
            }

            int singer = Integer.parseInt(singers[0]);

            String[] genres = parameterMap.get(GENRE_PARAM_NAME);

            if (genres == null) {
                throw new IllegalArgumentException("Choose genres");
            }

            int[] intGenre = Arrays.
                    stream(genres)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            String[] aboutUsers = parameterMap.get(ABOUT_USER_PARAM_NAME);

            if(aboutUsers == null) {
                throw new IllegalArgumentException("Enter info about you");
            }

            String aboutUser = aboutUsers[0];

            String [] emails = parameterMap.get(EMAIL_USER_PARAM_NAME);

            if(emails == null) {
                throw new IllegalArgumentException("Enter your email");
            }

            String email = emails[0];

            VoiceDTO voiceDTO = new VoiceDTO(singer, intGenre, aboutUser, email);

            service.save(voiceDTO);

            writer.write("Ответ сохранен");

            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/result");

        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }


    }
}