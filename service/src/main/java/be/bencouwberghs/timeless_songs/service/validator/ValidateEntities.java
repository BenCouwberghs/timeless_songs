package be.bencouwberghs.timeless_songs.service.validator;

import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import org.springframework.validation.ValidationUtils;

public class ValidateEntities {
    public void validateBand(BandDto bandDto) throws UserInputException  {
        if ( bandDto.getName() == null || bandDto.getWikiLinkPage() == null) {
            throw new UserInputException("Please fill in all needed properties");
        }
    }
}
