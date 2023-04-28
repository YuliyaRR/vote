package groupwork.dao.memory;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class VotingDaoMemory implements IVotingDao {

    private Set<SavedVoiceDTO> voices = new ConcurrentSkipListSet<>();

    @Override
    public synchronized List<SavedVoiceDTO> getVoiceList() {
        return new ArrayList<>(voices);
    }

    @Override
    public synchronized void save(SavedVoiceDTO voice) {
        voices.add(voice);
    }
}
