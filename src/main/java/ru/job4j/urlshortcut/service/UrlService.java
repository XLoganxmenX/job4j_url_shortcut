package ru.job4j.urlshortcut.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.dto.response.ShortUrlResponseDTO;
import ru.job4j.urlshortcut.dto.response.UrlStatisticDTO;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;

public interface UrlService {
    @Transactional
    ShortUrlResponseDTO convertAndSave(Site site, String url);

    @Transactional
    String findUrlFullPathByShortPath(String shortPath);

    List<UrlStatisticDTO> getAllUrlStatisticBySite(Site site);
}
