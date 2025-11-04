package be.bencouwberghs.timeless_songs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @Column(nullable = true)
    private int year;

    @Column(nullable = true)
    private String linkWikiPage;

    @Column(nullable = true)
    private String youTubeClipCode;

    @Column(nullable = true)
    private String genres;

    @Column(nullable = true)
    private int rating;
}
