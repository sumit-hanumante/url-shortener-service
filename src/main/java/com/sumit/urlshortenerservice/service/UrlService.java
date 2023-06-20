package com.sumit.urlshortenerservice.service;

import com.sumit.urlshortenerservice.model.Url;
import com.sumit.urlshortenerservice.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public Optional<Url> getUrlByShortUrl(String shortUrl) {
        return Optional.ofNullable(urlRepository.findByShortUrl(shortUrl));
    }

    public Url saveUrl(Url url) {
        if (url.getOriginalUrl() == null || url.getOriginalUrl().isEmpty()) {
            throw new IllegalArgumentException("Original URL cannot be null or empty");
        }

        String shortUrl = generateShortUrl();
        while (urlRepository.findByShortUrl(shortUrl) != null) {
            shortUrl = generateShortUrl(); // Keep generating until we get a unique shortUrl
        }

        url.setShortUrl(shortUrl);

        return urlRepository.save(url);
    }

    private String generateShortUrl() {
        String shortUrl;
        do {
            shortUrl = generateRandomString();
        } while (urlRepository.findByShortUrl(shortUrl) != null);
        return shortUrl;
    }

    private String generateRandomString() {
        // Generate a 6-character long string
        int length = 6;
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
        }
        return sb.toString();
    }



    public void deleteUrl(Long id) {
        try {
            urlRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("URL with id " + id + " not found");
        }
    }

    public Url updateUrl(Long id, Url newUrlData) {
        return urlRepository.findById(id)
                .map(url -> {
                    url.setOriginalUrl(newUrlData.getOriginalUrl());
                    url.setShortUrl(newUrlData.getShortUrl());
                    url.setUserId(newUrlData.getUserId());
                    url.setCreationDate(newUrlData.getCreationDate());
                    url.setExpirationDate(newUrlData.getExpirationDate());
                    url.setPassword(newUrlData.getPassword());
                    url.setClicks(newUrlData.getClicks());
                    return urlRepository.save(url);
                })
                .orElseThrow(() -> new RuntimeException("URL with id " + id + " not found"));
    }
}
