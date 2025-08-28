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
    @JoinColumn(name = "band_id", nullable = true)
    private Band band;

    @Column(nullable = true)
    private int year;

    @Column(nullable = true)
    private String linkWikiPage;
}
