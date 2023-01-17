package groupwork.service;

import groupwork.dto.AllStatisticDTO;
import groupwork.dao.api.IStatisticDAO_DB;
import groupwork.service.api.IStatisticsService;

public class StatisticsService_DB implements IStatisticsService {

    private final IStatisticDAO_DB daoDb;

    public StatisticsService_DB(IStatisticDAO_DB daoDb) {
        this.daoDb = daoDb;
    }

    @Override
    public AllStatisticDTO getAllSort() {
        return new AllStatisticDTO(daoDb.getSingerVote(), daoDb.getGenreVote(), daoDb.getAboutVote());
    }
}
