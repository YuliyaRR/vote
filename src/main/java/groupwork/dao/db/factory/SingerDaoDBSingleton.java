package groupwork.dao.db.factory;

import groupwork.dao.db.SingerDAO_DB;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.db.ds.factory.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class SingerDaoDBSingleton {
    private volatile static SingerDAO_DB instance;

    private SingerDaoDBSingleton() {
    }

    public static ISingerDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (SingerDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new SingerDAO_DB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }


}
