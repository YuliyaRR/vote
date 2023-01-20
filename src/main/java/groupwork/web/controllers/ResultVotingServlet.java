package groupwork.web;

import groupwork.dto.GenreDTO;
import groupwork.dto.SingerDTO;
import groupwork.dto.AllStatisticDTO;
import groupwork.service.api.IStatisticsService;
import groupwork.service.fabrics.StatisticServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet(name = "ResultVotingServlet", urlPatterns = "/result")
public class ResultVotingServlet extends HttpServlet {
    private final IStatisticsService statisticsService;

    public ResultVotingServlet() {
        this.statisticsService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        PrintWriter writer = resp.getWriter();

        AllStatisticDTO allSort = statisticsService.getAllSort();

        writer.write("<p>Результаты голосования за лучшего исполнителя:</p>");
        for (Map.Entry<SingerDTO, Integer> entry : allSort.getMapSingers().entrySet()) {
            writer.write("<p>" + entry.getKey() + " -> " + entry.getValue() + "</p>");
        }

        writer.write("<p>Результаты голосования за лучший жанр:</p>");
        for (Map.Entry<GenreDTO, Integer> entry : allSort.getMapGenres().entrySet()) {
            writer.write("<p>" + entry.getKey() + " -> " + entry.getValue() + "</p>");
        }

        writer.write("<p>О пользователе:</p>");
        for (Map.Entry<String, LocalDateTime> entry : allSort.getMapUserInfo().entrySet()) {
            writer.write("<p>" + entry.getKey() + " -> " + dtf.format(entry.getValue()) + "</p>");
        }

    }
}
