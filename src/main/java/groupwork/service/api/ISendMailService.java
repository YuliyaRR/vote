package groupwork.service.api;

import groupwork.dto.SavedVoiceDTO;

public interface ISendMailService {

    void send(SavedVoiceDTO savedVoiceDTO);
}
