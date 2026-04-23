package io.github.wlailson.shortcuturl.infrastructure.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlRequestDTO(
        @NotBlank(message = "A URL não pode estar vazia")
        @URL(message = "O formato da URL é invalido")
        String originalUrl
) {
}
