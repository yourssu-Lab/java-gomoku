package com.yourssu.config;

import com.yourssu.domain.Game;
import com.yourssu.domain.Board;
import com.yourssu.domain.Move;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class GameConfig {
    @Bean
    public Map<UUID, Game> games() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public Map<UUID, Board> boards() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    Map<UUID, List<Move>> moves() {
        return new ConcurrentHashMap<>();
    }
}
