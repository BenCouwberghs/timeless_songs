package be.bencouwberghs.timeless_songs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditableEntity {

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dateLastModified = LocalDateTime.now();
}
