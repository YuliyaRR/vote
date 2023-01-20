package groupwork.dao.memory;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class VotingDao implements IVotingDao {

    private Set<SavedVoiceDTO> voices = new ConcurrentSkipListSet<>();

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        return new ArrayList<>(voices);
    }

    @Override
    public void save(SavedVoiceDTO voice) {
        voices.add(voice);
    }
}
