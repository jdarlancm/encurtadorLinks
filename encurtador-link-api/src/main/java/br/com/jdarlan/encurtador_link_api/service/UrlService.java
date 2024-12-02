package br.com.jdarlan.encurtador_link_api.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jdarlan.encurtador_link_api.helper.UrlHelper;
import br.com.jdarlan.encurtador_link_api.model.Url;
import br.com.jdarlan.encurtador_link_api.repository.UrlRepository;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    public Url createShortUrl(String originalUrl) {
        if(!UrlHelper.isValidAndReachableUrl(originalUrl)) {
            throw new IllegalArgumentException("A URL fornecida é inválida");
        }
        String shortUrl = UUID.randomUUID().toString().substring(0,8);
        Url url = new Url();
        url.setShortUrl(shortUrl);
        url.setOriginalUrl(originalUrl);
        url.setCreationDate(LocalDateTime.now());
        return repository.save(url);
    }
    
    @Transactional
    public Optional<String> getOriginalUrl(String shorUrl)  {
        return repository.findByShortUrl(shorUrl)
            .map(url -> {
                url.setAccessCount(url.getAccessCount() + 1);
                repository.save(url);
                return url.getOriginalUrl();
            });
    }

}
