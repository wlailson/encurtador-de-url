package io.github.wlailson.shortcuturl.domain.service;

import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;
import io.github.wlailson.shortcuturl.domain.repository.ShortedUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class ShortedUrlService {

    private final ShortedUrlRepository repository;
    private static final String permitedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGHT = 6;
    private final SecureRandom random = new SecureRandom();

    public ShortedUrl shortdUrl(String originalUrl) {
        String generatedCode;
        do {
            generatedCode = generateRandomCode();
        }
        while (repository.existsByShortCode(generatedCode));
        {
            ShortedUrl newUrl = ShortedUrl
                    .builder()
                    .originalUrl(originalUrl)
                    .shortCode(generatedCode)
                    .build();
            return repository.save(newUrl);
        }
    }

    public String recoverOriginalString(String shorCode) {
        return repository.findByShortCode(shorCode)
                .map(ShortedUrl::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL não encontrada"));
    }

    public String generateRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGHT);
        for (int i = 0; i < CODE_LENGHT; i++) {
            int randomIndex = random.nextInt(permitedCharacters.length());
            sb.append(permitedCharacters.charAt(randomIndex));
        }
        return sb.toString();
    }

}

