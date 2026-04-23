package io.github.wlailson.shortcuturl.infrastructure.dtos;

import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;

public record UrlResponseDTO(String originalUrl, String shortedUrl) {

    public UrlResponseDTO(ShortedUrl entity, String baseDomain){
        this(entity.getOriginalUrl(), baseDomain + "/" + entity.getShortCode());
    }
}
