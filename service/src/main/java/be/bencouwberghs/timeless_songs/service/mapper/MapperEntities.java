package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import org.springframework.stereotype.Service;

@Service
public class MapperEntities {
    public BandDto mapBandEntityToDto(Band band) {
        return BandDto.builder()
                .id(band.getId())
                .name(band.getName())
                .wikiLinkPage(band.getLinkWikiPage())
                .build();
    }

    public Band mapBandDtoToBandEntity(BandDto bandDto) {
        return Band.builder()
                .id(bandDto.getId())
                .name(bandDto.getName())
                .linkWikiPage(bandDto.getWikiLinkPage())
                .build();
    }

    public SongDto mapSongEntityToDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .band(song.getBand())
                .year(song.getYear())
                .wikiLinkPage(song.getLinkWikiPage())
                .build();
    }

    public Song mapSongDtoToSongEntity(SongDto songDto) {
        return Song.builder()
                .id(songDto.getId())
                .name(songDto.getName())
                .band(songDto.getBand())
                .year(songDto.getYear())
                .linkWikiPage(songDto.getWikiLinkPage())
                .build();
    }
}
