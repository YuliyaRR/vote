package groupwork.dao.fabrics;

import groupwork.dao.memory.VotingDao;
import groupwork.dao.api.IVotingDao;


public class VotingDaoSingleton {
    private volatile static VotingDao instance;

    private VotingDaoSingleton() {
    }

    public static IVotingDao getInstance() {
        if (instance == null) {
            synchronized (VotingDaoSingleton.class) {
                if (instance == null) {
                    instance = new VotingDao();
                }
            }
        }
        return instance;
    }
}
