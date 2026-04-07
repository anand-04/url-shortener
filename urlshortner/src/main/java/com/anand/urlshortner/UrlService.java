package com.anand.urlshortner;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
        public Url shortenUrl(String originalUrl) {
            String shortCode = UUID.randomUUID().toString().substring(0, 6);
            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortCode(shortCode);
            return urlRepository.save(url);
        }

        public String getOriginalUrl(String shortCode) {
            return urlRepository.findByShortCode(shortCode)
                    .map(Url::getOriginalUrl)
                    .orElseThrow(() -> new RuntimeException("URL not found"));
        }
    }

