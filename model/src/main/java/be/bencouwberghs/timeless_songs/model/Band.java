package be.bencouwberghs.timeless_songs.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Band extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String linkWikiPage;

    @Column(nullable = true, length = 1024)
    private String comments;

    @OneToMany(mappedBy = "band")
    @Column(nullable = true)
    @Builder.Default
    private List<Song> songs = new ArrayList<>();

}
