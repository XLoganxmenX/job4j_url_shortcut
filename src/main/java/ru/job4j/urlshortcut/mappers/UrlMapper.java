package ru.job4j.urlshortcut.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.urlshortcut.dto.response.UrlStatisticDTO;
import ru.job4j.urlshortcut.model.Url;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    @Mapping(source = "fullPath", target = "url")
    @Mapping(source = "callCount", target = "total")
    UrlStatisticDTO getUrlStatisticDtoFromUrl(Url url);
}
