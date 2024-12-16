package ru.job4j.urlshortcut.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteLoginRequestDTO {
    @NotBlank(message = "Поле login не должно быть пустым")
    private String login;

    @NotBlank(message = "Поле password не должно быть пустым")
    private String password;
}
