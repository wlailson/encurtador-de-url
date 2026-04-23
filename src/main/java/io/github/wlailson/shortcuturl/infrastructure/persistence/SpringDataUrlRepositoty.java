package io.github.wlailson.shortcuturl.infrastructure.persistence;

import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface SpringDataUrlRepositoty extends JpaRepository<ShortedUrl, Long> {
    Optional<ShortedUrl> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);

}
