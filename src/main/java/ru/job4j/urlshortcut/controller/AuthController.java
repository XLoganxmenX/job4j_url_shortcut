package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.request.SiteLoginRequestDTO;
import ru.job4j.urlshortcut.dto.request.SiteRegistrationRequestDTO;
import ru.job4j.urlshortcut.dto.response.JwtResponseDTO;
import ru.job4j.urlshortcut.dto.response.SiteRegistrationResponseDTO;
import ru.job4j.urlshortcut.security.JwtUtils;
import ru.job4j.urlshortcut.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/url-shortcut/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/registration")
    public ResponseEntity<SiteRegistrationResponseDTO> registerSite(@Valid @RequestBody SiteRegistrationRequestDTO siteDto) {
        SiteRegistrationResponseDTO responseDto = authService.registerSite(siteDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateSite(@Valid @RequestBody SiteLoginRequestDTO siteRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        siteRequest.getLogin(), siteRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponseDTO(jwt, siteRequest.getLogin()));
    }
}
