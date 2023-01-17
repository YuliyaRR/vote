package groupwork.dao.fabrics;

import groupwork.dao.VotingDAO_DB;
import groupwork.dao.VotingDao;
import groupwork.dao.api.IVotingDao;


public class VotingDaoDBSingleton {
    private volatile static VotingDAO_DB instance;

    private VotingDaoDBSingleton() {
    }

    public static IVotingDao getInstance() {
        if (instance == null) {
            synchronized (VotingDaoDBSingleton.class) {
                if (instance == null) {
                    instance = new VotingDAO_DB();
                }
            }
        }
        return instance;
    }
}
