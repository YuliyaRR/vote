package groupwork.dao.fabrics;

import groupwork.dao.StatisticDAO_DB;
import groupwork.dao.api.IStatisticDAO_DB;

public class StatisticDaoDBSingleton {
    private volatile static StatisticDAO_DB instance;

    private StatisticDaoDBSingleton() {
    }

    public static IStatisticDAO_DB getInstance(){
        if(instance == null) {
            synchronized (StatisticDaoDBSingleton.class){
                if(instance == null) {
                    instance = new StatisticDAO_DB();
                }
            }
        }
        return instance;
    }
}
