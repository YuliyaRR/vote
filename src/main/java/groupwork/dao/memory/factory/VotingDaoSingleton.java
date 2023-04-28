package groupwork.dao.memory.factory;

import groupwork.dao.memory.VotingDaoMemory;
import groupwork.dao.api.IVotingDao;


public class VotingDaoSingleton {
    private volatile static VotingDaoMemory instance;

    private VotingDaoSingleton() {
    }

    public static IVotingDao getInstance() {
        if (instance == null) {
            synchronized (VotingDaoSingleton.class) {
                if (instance == null) {
                    instance = new VotingDaoMemory();
                }
            }
        }
        return instance;
    }
}
