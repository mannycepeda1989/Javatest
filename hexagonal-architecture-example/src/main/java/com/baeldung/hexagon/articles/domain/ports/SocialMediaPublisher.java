package com.baeldung.hexagon.articles.domain.ports;

import com.baeldung.hexagon.articles.domain.model.Article;

public interface SocialMediaPublisher {

    void publish(Article article);

}
