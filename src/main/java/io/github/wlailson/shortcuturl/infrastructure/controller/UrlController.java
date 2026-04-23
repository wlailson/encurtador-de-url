package io.github.wlailson.shortcuturl.infrastructure.controller;

import io.github.wlailson.shortcuturl.domain.entity.ShortedUrl;
import io.github.wlailson.shortcuturl.domain.service.ShortedUrlService;
import io.github.wlailson.shortcuturl.infrastructure.dtos.UrlRequestDTO;
import io.github.wlailson.shortcuturl.infrastructure.dtos.UrlResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UrlController {

    private final ShortedUrlService service;

    @PostMapping("/api/urls")
    public ResponseEntity<UrlResponseDTO> createShortedUrl(@RequestBody @Valid UrlRequestDTO dto, HttpServletRequest request){
        ShortedUrl urlSaved = service.shortdUrl(dto.originalUrl());

        String baseDomain = ServletUriComponentsBuilder.fromContextPath(request).build().toUriString();

        UrlResponseDTO response = new UrlResponseDTO(urlSaved, baseDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = service.recoverOriginalString(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }
}
