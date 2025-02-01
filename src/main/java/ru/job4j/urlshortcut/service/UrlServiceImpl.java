package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.dto.response.ShortUrlResponseDTO;
import ru.job4j.urlshortcut.dto.response.UrlStatisticDTO;
import ru.job4j.urlshortcut.mappers.UrlMapper;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.utils.CodeGenerator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    private final UrlMapper urlMapper;

    @Override
    public ShortUrlResponseDTO convertAndSave(Site site, String url) {
        String shortUrl = CodeGenerator.generateCodeFrom(url);
        Url newUrl = Url.builder()
                        .id(null)
                        .fullPath(url)
                        .shortPath(shortUrl)
                        .site(site)
                        .callCount(0L)
                        .build();
        urlRepository.save(newUrl);
        return new ShortUrlResponseDTO(shortUrl);
    }

    @Override
    @Transactional
    public String findUrlFullPathByShortPath(String shortPath) {
        Url url = urlRepository.findUrlByShortPath(shortPath)
                .orElseThrow(() -> new NoSuchElementException("Url with shortPath - " + shortPath + " not found"));
        urlRepository.incrementCallCountById(url.getId());
        return url.getFullPath();
    }

    @Override
    public List<UrlStatisticDTO> getAllUrlStatisticBySite(Site site) {
        return urlRepository.findAllBySite(site).stream()
                .map(urlMapper::getUrlStatisticDtoFromUrl)
                .toList();
    }
}
