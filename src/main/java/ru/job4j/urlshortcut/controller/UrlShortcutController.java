package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.request.UrlConvertRequestDTO;
import ru.job4j.urlshortcut.dto.response.ShortUrlResponseDTO;
import ru.job4j.urlshortcut.dto.response.UrlStatisticDTO;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.security.UserDetailsImpl;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/url-shortcut")
@AllArgsConstructor
public class UrlShortcutController {
    private final UrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<ShortUrlResponseDTO> convert(@Valid @RequestBody UrlConvertRequestDTO urlDto) {
        ShortUrlResponseDTO responseDto = urlService.convertAndSave(getSiteFromAuth(), urlDto.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@NotBlank @PathVariable("code") String code) {
        String urlFullPath = urlService.findUrlFullPathByShortPath(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlFullPath))
                .build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<UrlStatisticDTO>> statistic() {
        List<UrlStatisticDTO> urlStatistics = urlService.getAllUrlStatisticBySite(getSiteFromAuth());
        return ResponseEntity.ok()
                .body(urlStatistics);
    }

    private Site getSiteFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getSite();
    }
}
