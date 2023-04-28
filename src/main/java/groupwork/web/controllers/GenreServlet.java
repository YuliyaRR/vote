package groupwork.web.controllers;

import groupwork.dto.GenreDTO;
import groupwork.service.api.IGenreService;
import groupwork.service.fabrics.GenresServiceSingleton;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GenreServlet", urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {
    private final String GENRE_PARAM_NAME = "genre";
    private final String NEW_GENRE_NAME_PARAM_NAME = "newGenreName";
    private final IGenreService genreService;

    public GenreServlet() {
        this.genreService = GenresServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        List<GenreDTO> genreDTOS = genreService.get();

        PrintWriter writer = resp.getWriter();

        genreDTOS.forEach(genreDTO -> writer.write("<p>" + genreDTO + "</p>"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] genres = parameterMap.get(GENRE_PARAM_NAME);

        try{
            if (genres == null || genres.length > 1) {
                throw new IllegalArgumentException("Entered one genre");
            }

            int genre = Integer.parseInt(genres[0]);

            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setId(genre);

            genreService.delete(genreDTO);

            writer.write("<p>Genre deleted successfully</p>");

        }  catch (RuntimeException e){
            writer.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] genres = parameterMap.get(GENRE_PARAM_NAME);

        try {
            if (genres == null || genres.length > 1) {
                throw new IllegalArgumentException("Enter one genre to create");
            }

            String genreName = genres[0];

            GenreDTO genreDTO = new GenreDTO();
            genreDTO.setName(genreName);

            genreService.create(genreDTO);
            writer.write("<p>Genre created successfully</p>");
            resp.setStatus(201);

        } catch (RuntimeException e){
            writer.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String[] genres = parameterMap.get(GENRE_PARAM_NAME);
        String[] newGenres = parameterMap.get(NEW_GENRE_NAME_PARAM_NAME);

        try {
            if (genres == null || genres.length > 1 || newGenres == null || newGenres.length > 1) {
                throw new IllegalArgumentException("Enter one genre to update");
            }

            int genreID = Integer.parseInt(genres[0]);
            String newGenre = newGenres[0];

            genreService.update(new GenreDTO(newGenre, genreID));
            writer.write("<p>Genre updated successfully</p>");

        } catch (RuntimeException e){
            if (e.getCause() != null) {
                writer.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                writer.write("<p>" + e.getMessage() + "</p>");
            }

        }
    }



}
