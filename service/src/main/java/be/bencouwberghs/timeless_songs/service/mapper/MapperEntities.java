package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Genre;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
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
                .pinned(band.isPinned())
                .build();
    }

    public BandDto mapBandEntityToDtoShallow(Band band) {
        return BandDto.builder()
                .id(band.getId())
                .name(band.getName())
                .linkWikiPage(band.getLinkWikiPage())
                .comments(band.getComments())
                .pinned(band.isPinned())
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
                .pinned(bandDto.isPinned())
                .build();
    }

    public Band updateBandEntityFromDto(Band band, BandDto bandDto) {
        band.setName(bandDto.getName());
        band.setLinkWikiPage(bandDto.getLinkWikiPage());
        band.setComments(bandDto.getComments());
        band.setPinned(bandDto.isPinned());

        return band;
    }

    public SongDto mapSongEntityToDto(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .name(song.getName())
                .bandDto(mapBandEntityToDtoShallow(song.getBand()))
                .year(song.getYear())
                .wikiLinkPage(song.getLinkWikiPage())
                .youTubeClipCode(song.getYouTubeClipCode())
                .genres(song.getGenres())
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
                .youTubeClipCode(songDto.getYouTubeClipCode())
                .genres(songDto.getGenres())
                .build();
    }

    public Song updateSongEntityFromDto(Song song, SongDto songDto) {
        song.setName(songDto.getName());
        song.setBand(mapBandDtoToBandEntity(songDto.getBandDto()));
        song.setYear(songDto.getYear());
        song.setLinkWikiPage(songDto.getWikiLinkPage());
        song.setYouTubeClipCode(songDto.getYouTubeClipCode());
        song.setGenres(songDto.getGenres());

        return song;
    }

    public GenreDto mapGenreEntityToGenreDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .description(genre.getDescription())
                .build();
    }

    public List<GenreDto> mapGenreEntitiesToDtos(List<Genre> genres) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Genre genre : genres) {
            genreDtos.add(mapGenreEntityToGenreDto(genre));
        }
        return genreDtos;
    }
}
