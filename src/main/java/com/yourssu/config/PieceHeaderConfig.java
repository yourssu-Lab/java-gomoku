package com.yourssu.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yourssu.resolver.PieceHeaderResolver;

import java.util.List;

@Configuration
public class PieceHeaderConfig implements WebMvcConfigurer {
    private final PieceHeaderResolver pieceHeaderResolver;

    public PieceHeaderConfig(PieceHeaderResolver pieceHeaderResolver) {
        this.pieceHeaderResolver = pieceHeaderResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pieceHeaderResolver);
    }
}