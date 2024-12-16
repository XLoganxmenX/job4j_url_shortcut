package ru.job4j.urlshortcut.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlConvertRequestDTO {
    @NotBlank(message = "Поле url не должно быть пустым")
    private String url;
}
