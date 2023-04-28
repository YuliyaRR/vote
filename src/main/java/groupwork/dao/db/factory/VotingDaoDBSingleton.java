package groupwork.dao.db.factory;

import groupwork.dao.db.VotingDAO_DB;
import groupwork.dao.api.IVotingDao;
import groupwork.dao.db.ds.factory.DataSourceSingleton;

import java.beans.PropertyVetoException;


public class VotingDaoDBSingleton {
    private volatile static VotingDAO_DB instance;

    private VotingDaoDBSingleton() {
    }

    public static IVotingDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (VotingDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new VotingDAO_DB(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
