package groupwork.service.fabrics;


import groupwork.service.StatisticsService;
import groupwork.service.api.IStatisticsService;

public class StatisticServiceSingleton {
    private volatile static StatisticsService instance;

    private StatisticServiceSingleton() {

    }

    public static IStatisticsService getInstance() {
        if (instance == null) {
            synchronized (StatisticServiceSingleton.class) {
                if (instance == null) {
                    instance = new StatisticsService(VoteServiceSingleton.getInstance(),
                            SingersServiceSingleton.getInstance(),
                            GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
