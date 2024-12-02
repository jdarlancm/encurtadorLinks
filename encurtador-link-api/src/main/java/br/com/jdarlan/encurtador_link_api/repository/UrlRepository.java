package br.com.jdarlan.encurtador_link_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdarlan.encurtador_link_api.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shorUrl);
    
}
