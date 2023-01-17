package groupwork.service.api;

import groupwork.dto.SavedVoiceDTO;
import groupwork.dto.VoiceDTO;

import java.util.List;

public interface IVotesService {

    void save(VoiceDTO voice);

    List<SavedVoiceDTO> get();

}
