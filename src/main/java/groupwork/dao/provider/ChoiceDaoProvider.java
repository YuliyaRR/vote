package groupwork.dao.provider;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;
import groupwork.dao.provider.api.IDaoProvider;

public class ChoiceDaoProvider implements IDaoProvider {

    private static volatile ChoiceDaoProvider instance;
    private boolean useDB = true;//попробовать сделать enum

    private IDaoProvider daoProvider;

    private ChoiceDaoProvider() {
        if(useDB){
            daoProvider = new DaoDBProvider();
        } else {
            daoProvider = new DaoMemoryProvider();
        }
    }

    @Override
    public IGenreDao genreDao() {
        return daoProvider.genreDao();
    }

    @Override
    public ISingerDao singerDao() {
        return daoProvider.singerDao();
    }

    @Override
    public IVotingDao votingDao() {
        return daoProvider.votingDao();
    }

    public static IDaoProvider getInstance() {
        if(instance == null){
            synchronized (ChoiceDaoProvider.class){
                if(instance == null){
                    instance = new ChoiceDaoProvider();
                }
            }
        }
        return instance;
    }
}
