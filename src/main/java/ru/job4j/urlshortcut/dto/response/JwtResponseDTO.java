package ru.job4j.urlshortcut.dto.response;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";
    private String login;

    public JwtResponseDTO(String accessToken, String login) {
        this.token = accessToken;
        this.login = login;
    }
}
