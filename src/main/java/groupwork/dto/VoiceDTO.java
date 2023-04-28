package groupwork.dto;

import java.util.Arrays;
import java.util.Objects;

public class VoiceDTO {
    private int singer;
    private int[] genre;
    private String message;
    private String email;

    public VoiceDTO(int singer, int[] genre, String message, String email) {
        this.singer = singer;
        this.genre = genre;
        this.message = message;
        this.email = email;
    }

    public int getSinger() {
        return singer;
    }

    public int[] getGenre() {
        return genre;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoiceDTO voiceDTO = (VoiceDTO) o;
        return singer == voiceDTO.singer
                && Arrays.equals(genre, voiceDTO.genre)
                && Objects.equals(message, voiceDTO.message)
                && Objects.equals(email, voiceDTO.email);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(singer, message, email);
        result = 31 * result + Arrays.hashCode(genre);
        return result;
    }

    @Override
    public String toString() {
        return "Accepted voice: " +
                "singer = " + singer +
                ", genre = " + Arrays.toString(genre) +
                ", message = " + message +
                ", email = " + email;
    }
}
