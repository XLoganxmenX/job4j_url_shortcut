package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "urls")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_path")
    private String fullPath;

    @Column(name = "short_path")
    private String shortPath;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @Column(name = "call_count")
    private Long callCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url = (Url) o;
        return Objects.equals(id, url.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
