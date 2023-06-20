package com.sumit.urlshortenerservice;

import com.sumit.urlshortenerservice.model.Url;
import com.sumit.urlshortenerservice.repository.UrlRepository;
import com.sumit.urlshortenerservice.service.UrlService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UrlServiceTest {

    private UrlRepository urlRepository = mock(UrlRepository.class);
    private UrlService urlService = new UrlService(urlRepository);

    @Test
    public void testSaveUrl() {
        Url url = new Url();
        url.setOriginalUrl("http://example.com");

        when(urlRepository.save(any(Url.class))).thenReturn(url);

        Url savedUrl = urlService.saveUrl(url);

        verify(urlRepository, times(1)).save(url);
        assertEquals("http://example.com", savedUrl.getOriginalUrl());
    }
}
