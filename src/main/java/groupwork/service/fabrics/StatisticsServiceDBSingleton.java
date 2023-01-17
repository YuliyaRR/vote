package groupwork.service.fabrics;

import groupwork.service.StatisticsService;
import groupwork.service.api.IStatisticsService;

public class StatisticsServiceDBSingleton {
    private volatile static StatisticsService instance;

    private StatisticsServiceDBSingleton() {
    }

    public static IStatisticsService getInstance() {
        if(instance == null){
            synchronized (SingersServiceDBSingleton.class){
                if(instance == null){
                    instance = new StatisticsService(VoteServiceDBSingleton.getInstance(),
                            SingersServiceDBSingleton.getInstance(),
                            GenresServiceDBSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
