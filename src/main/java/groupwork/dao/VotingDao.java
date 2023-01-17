package groupwork.dao;

import groupwork.dao.api.IVotingDao;
import groupwork.dto.SavedVoiceDTO;

import java.util.ArrayList;
import java.util.List;

public class VotingDao implements IVotingDao {

    private List<SavedVoiceDTO> voices = new ArrayList<>();

    @Override
    public List<SavedVoiceDTO> getVoiceList() {
        return voices;
    }

    @Override
    public void save(SavedVoiceDTO voice) {
        voices.add(voice);
    }
}
