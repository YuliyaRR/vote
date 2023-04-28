package groupwork.dao.provider;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;
import groupwork.dao.db.factory.GenreDaoDBSingleton;
import groupwork.dao.db.factory.SingerDaoDBSingleton;
import groupwork.dao.db.factory.VotingDaoDBSingleton;
import groupwork.dao.provider.api.IDaoProvider;

import java.beans.PropertyVetoException;

public class DaoDBProvider implements IDaoProvider {

    @Override
    public IGenreDao genreDao() {
        try {
            return GenreDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public ISingerDao singerDao() {
        try {
            return SingerDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public IVotingDao votingDao() {
        try {
            return VotingDaoDBSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new IllegalStateException(e);
        }
    }
}
