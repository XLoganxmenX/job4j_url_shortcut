package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.dto.request.SiteRegistrationRequestDTO;
import ru.job4j.urlshortcut.dto.response.SiteRegistrationResponseDTO;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.utils.CodeGenerator;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SiteRepository siteRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SiteRegistrationResponseDTO registerSite(SiteRegistrationRequestDTO siteDto) {
        String generatedLogin = generateLogin(siteDto.getSite());
        String generatedPassword = CodeGenerator.generatePassword();
        Site site = Site.builder()
                .id(null)
                .name(siteDto.getSite())
                .login(generatedLogin)
                .password(passwordEncoder.encode(generatedPassword))
                .build();
        siteRepository.save(site);
        return new SiteRegistrationResponseDTO(true, generatedLogin, generatedPassword);
    }

    private String generateLogin(String name) {
        return String.format("site/%s_%s", name, CodeGenerator.generateCodeFrom(name));
    }
}
