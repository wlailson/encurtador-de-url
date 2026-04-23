package io.github.wlailson.shortcuturl.infrastructure.persistence;

import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;
import io.github.wlailson.shortcuturl.domain.repository.ShortedUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UrlRepositoryAdapter implements ShortedUrlRepository {
    private final SpringDataUrlRepositoty jpaRepository;

    @Override
    public ShortedUrl save(ShortedUrl url) {
        return jpaRepository.save(url);
    }

    @Override
    public Optional<ShortedUrl> findByShortCode(String shortCode) {
        return jpaRepository.findByShortCode(shortCode);
    }

    @Override
    public boolean existsByShortCode(String shortCode) {
        return jpaRepository.existsByShortCode(shortCode);
    }
}
