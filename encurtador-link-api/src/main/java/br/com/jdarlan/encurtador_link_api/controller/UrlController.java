package br.com.jdarlan.encurtador_link_api.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdarlan.encurtador_link_api.model.Url;
import br.com.jdarlan.encurtador_link_api.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping
    public ResponseEntity<String> createShorUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        Url url = service.createShortUrl(originalUrl);
        return ResponseEntity.ok(url.getShortUrl());
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Optional<String> originalUrl = service.getOriginalUrl(shortUrl);
        if(originalUrl.isPresent()) {
            response.sendRedirect(originalUrl.get());
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        return ResponseEntity.notFound().build();
    }

}
