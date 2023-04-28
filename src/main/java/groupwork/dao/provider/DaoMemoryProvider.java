package groupwork.dao.provider;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;
import groupwork.dao.memory.factory.GenreDaoSingleton;
import groupwork.dao.memory.factory.SingerDaoSingleton;
import groupwork.dao.memory.factory.VotingDaoSingleton;
import groupwork.dao.provider.api.IDaoProvider;

public class DaoMemoryProvider implements IDaoProvider {

    @Override
    public IGenreDao genreDao() {
        return GenreDaoSingleton.getInstance();
    }

    @Override
    public ISingerDao singerDao() {
        return SingerDaoSingleton.getInstance();
    }

    @Override
    public IVotingDao votingDao() {
        return VotingDaoSingleton.getInstance();
    }
}
