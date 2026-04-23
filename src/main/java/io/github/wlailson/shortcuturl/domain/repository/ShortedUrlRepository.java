package io.github.wlailson.shortcuturl.domain.repository;


import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface ShortedUrlRepository {
    ShortedUrl save(ShortedUrl url);

    Optional<ShortedUrl> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

}
