package groupwork.web;

import groupwork.dto.SingerDTO;
import groupwork.service.api.ISingerService;
import groupwork.service.fabrics.SingersServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SingerServlet", urlPatterns = "/singer")
public class SingerServlet extends HttpServlet {
    private final String SINGER_PARAM_NAME = "singer";
    private final String NEW_SINGER_NAME_PARAM_NAME = "newSingerName";

    private final ISingerService singerService;

    public SingerServlet() {
        this.singerService = SingersServiceSingleton.getInstance();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] singers = parameterMap.get(SINGER_PARAM_NAME);
        String[] newSingers = parameterMap.get(NEW_SINGER_NAME_PARAM_NAME);

        try {
            if (singers == null || singers.length > 1 || newSingers == null || newSingers.length > 1) {
                throw new IllegalArgumentException("Enter one singer to update");
            }

            int singerID = Integer.parseInt(singers[0]);
            String newSinger = newSingers[0];

            singerService.update(new SingerDTO(newSinger, singerID));
            writer.write("<p>Singer updated successfully</p>");

        } catch (RuntimeException e){
        writer.write("<p>" + e.getMessage() + "</p>");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] singers = parameterMap.get(SINGER_PARAM_NAME);

        try {
            if (singers == null || singers.length > 1) {
                throw new IllegalArgumentException("Enter one singer to create");
            }

            String singerName = singers[0];

            singerService.create(new SingerDTO(singerName));
            writer.write("<p>Singer created successfully</p>");

        } catch (RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] singers = parameterMap.get(SINGER_PARAM_NAME);

        try{
            if (singers == null || singers.length > 1) {
                throw new IllegalArgumentException("Entered one singer");
            }

            int singer = Integer.parseInt(singers[0]);

            SingerDTO singerDTO = new SingerDTO();
            singerDTO.setId(singer);

            singerService.delete(singerDTO);

            writer.write("<p>Singer deleted successfully</p>");

        }  catch (RuntimeException e){
            writer.write("<p>" + e.getMessage() + "</p>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<SingerDTO> singerDTOS = singerService.get();

        PrintWriter writer = resp.getWriter();

        singerDTOS.forEach(singerDTO -> writer.write("<p>" + singerDTO + "</p>"));
    }
}