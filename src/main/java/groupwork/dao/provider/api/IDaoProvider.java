package groupwork.dao.provider.api;

import groupwork.dao.api.IGenreDao;
import groupwork.dao.api.ISingerDao;
import groupwork.dao.api.IVotingDao;

public interface IDaoProvider {

    IGenreDao genreDao();

    ISingerDao singerDao();

    IVotingDao votingDao();
}
