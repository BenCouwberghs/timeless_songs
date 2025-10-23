package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperEntities {
    public BandDto mapBandEntityToDto(Band band) {
        return BandDto.builder()
                .id(band.getId())
                .name(band.getName())
                .linkWikiPage(band.getLinkWikiPage())
                .comments(band.getComments())
                .songDtos(mapSongEntitiesToDtos(band.getSongs()))
                .build();
    }

    public BandDto mapBandEntityToDtoShallow(Band band) {
        return BandDto.builder()
                .id(band.getId())
                .name(band.getName())
                .linkWikiPage(band.getLinkWikiPage())
                .comments(band.getComments())
                .build();
    }

    public List<BandDto> mapBandEntitiesToDtos(List<Band> bands) {
        List<BandDto> bandDtos = new ArrayList<>();
        for (Band band : bands) {
            bandDtos.add(mapBandEntityToDto(band));
        }
        return bandDtos;
    }

    public Band mapBandDtoToBandEntity(BandDto bandDto) {
        return Band.builder()
                .id(bandDto.getId())
                .name(bandDto.getName())
                .linkWikiPage(bandDto.getLinkWikiPage())
                .comments(bandDto.getComments())
                .build();
    }

    public Band updateBandEntityFromDto(Band band, BandDto bandDto) {
        band.setName(bandDto.getName());
        band.setLinkWikiPage(bandDto.getLinkWikiPage());
        band.setComments(bandDto.getComments());

        return band;
    }

    public SongDto mapSongEntityToDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .bandDto(mapBandEntityToDtoShallow(song.getBand()))
                .year(song.getYear())
                .wikiLinkPage(song.getLinkWikiPage())
                .build();
    }

    public List<SongDto> mapSongEntitiesToDtos(List<Song> songs) {
        List<SongDto> songDtos = new ArrayList<>();
        for (Song song : songs) {
            songDtos.add(mapSongEntityToDto(song));
        }
        return songDtos;
    }

    public Song mapSongDtoToSongEntity(SongDto songDto) {
        return Song.builder()
                .id(songDto.getId())
                .name(songDto.getName())
                .band(mapBandDtoToBandEntity(songDto.getBandDto()))
                .year(songDto.getYear())
                .linkWikiPage(songDto.getWikiLinkPage())
                .build();
    }

    public Song updateSongEntityFromDto(Song song, SongDto songDto) {
        song.setName(songDto.getName());
        song.setBand(mapBandDtoToBandEntity(songDto.getBandDto()));
        song.setYear(songDto.getYear());
        song.setLinkWikiPage(songDto.getWikiLinkPage());

        return song;
    }
}
