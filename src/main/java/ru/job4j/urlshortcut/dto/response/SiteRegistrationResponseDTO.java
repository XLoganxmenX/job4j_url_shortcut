package ru.job4j.urlshortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteRegistrationResponseDTO {
    private boolean registration;
    private String login;
    private String password;
}
