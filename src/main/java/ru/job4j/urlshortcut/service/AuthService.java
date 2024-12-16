package ru.job4j.urlshortcut.service;

import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.dto.request.SiteRegistrationRequestDTO;
import ru.job4j.urlshortcut.dto.response.SiteRegistrationResponseDTO;

public interface AuthService {
    @Transactional
    SiteRegistrationResponseDTO registerSite(SiteRegistrationRequestDTO siteDto);
}
