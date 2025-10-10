package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final MapperEntities mapperEntities;

    public SongServiceImpl(SongRepository songRepository, MapperEntities mapperEntities) {
        this.songRepository = songRepository;
        this.mapperEntities = mapperEntities;
    }

    public void addSong(SongDto songDto) {
        Song song = mapperEntities.mapSongDtoToSongEntity(songDto);
        if (songRepository.existsByName(song.getName())) {
            throw new EntityExistsException("Song name taken: " + song.getName());
        }
        songRepository.save(song);
    }

    public void modifySong(SongDto songDto, Long id) {
        Song song = songRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Song not found with ID: " + id));

        if (songRepository.existsByNameAndIdNot(song.getName(), song.getId())) {
            throw new EntityExistsException("Changed song name is already taken: " + song.getName());
        }

        mapperEntities.updateSongEntityFromDto(song, songDto);

        songRepository.save(song);
    }

    public void deleteSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Song not found with id:" + id));
        songRepository.delete(song);
    }

    public SongDto fetchSong(Long id) {
        return mapperEntities.mapSongEntityToDto(songRepository.getReferenceById(id));
    }

    public List<SongDto> fetchAllSongs() {
        return mapperEntities.mapSongEntitiesToDtos(songRepository.findAll());
    }

    public List<SongDto> fetchAllSongsOfBand(Band band) {
        return mapperEntities.mapSongEntitiesToDtos(songRepository.findAllByBand(band));
    }
}
