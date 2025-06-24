package be.bencouwberghs.timeless_songs.service.validator;

import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

@Service
public class ValidateEntities {
    public void validateBand(BandDto bandDto) throws UserInputException  {
        if ( bandDto.getName() == null || bandDto.getWikiLinkPage() == null) {
            throw new UserInputException("Please fill in all needed properties");
        }
    }

    public void validateSong(SongDto songDto) throws UserInputException {
        if (songDto.getName() == null || songDto.getYear() == 0 || songDto.getWikiLinkPage() == null) {
            throw new UserInputException("Please fill in all needed properties");
        }
    }
}
