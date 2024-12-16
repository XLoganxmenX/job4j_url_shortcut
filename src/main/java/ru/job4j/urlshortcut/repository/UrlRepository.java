package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findUrlByShortPath(String shortPath);

    @Modifying(clearAutomatically = true)
    @Query("""
           update Url u
           set u.callCount = u.callCount + 1
           where u.id = :id
           """)
    void incrementCallCountById(@Param("id") Long id);

    List<Url> findAllBySite(Site site);
}
