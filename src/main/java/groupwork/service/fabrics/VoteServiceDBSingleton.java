package groupwork.service.fabrics;

import groupwork.dao.fabrics.VotingDaoDBSingleton;
import groupwork.service.VoteService;
import groupwork.service.api.IVotesService;


public class VoteServiceDBSingleton {
    private volatile static VoteService instance;

    public static IVotesService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceDBSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(VotingDaoDBSingleton.getInstance(),
                            SingersServiceDBSingleton.getInstance(),
                            GenresServiceDBSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
