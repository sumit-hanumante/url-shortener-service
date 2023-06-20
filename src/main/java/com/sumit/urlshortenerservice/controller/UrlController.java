package com.sumit.urlshortenerservice.controller;

import com.sumit.urlshortenerservice.model.Url;
import com.sumit.urlshortenerservice.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public List<Url> getAllUrls() {
        return urlService.getAllUrls();
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Url> getUrlByShortUrl(@PathVariable String shortUrl) {
        return urlService.getUrlByShortUrl(shortUrl)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Url saveUrl(@RequestBody Url url) {
        return urlService.saveUrl(url);
    }

    @DeleteMapping("/{id}")
    public void deleteUrl(@PathVariable Long id) {
        urlService.deleteUrl(id);
    }

    @PutMapping("/{id}")
    public Url updateUrl(@PathVariable Long id, @RequestBody Url newUrlData) {
        return urlService.updateUrl(id, newUrlData);
    }
}
