package io.github.bencouwberghs.timeless_songs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = true)
    private Band band;

    @Column(nullable = true)
    private int year;

    @Column(nullable = true)
    private String linkWikiPage;
}
