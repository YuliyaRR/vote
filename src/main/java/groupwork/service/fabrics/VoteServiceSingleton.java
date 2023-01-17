package groupwork.service.fabrics;

import groupwork.dao.fabrics.VotingDaoSingleton;
import groupwork.service.VoteService;
import groupwork.service.api.IVotesService;


public class VoteServiceSingleton {
    private volatile static VoteService instance;

    public static IVotesService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceSingleton.class) {
                if (instance == null) {
                    instance = new VoteService(VotingDaoSingleton.getInstance(), SingersServiceSingleton.getInstance(), GenresServiceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}
