package com.team8.kanban.card;

import com.team8.kanban.domain.card.dto.CardResponse;
import com.team8.kanban.domain.card.service.CardService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardFindAllCacheTest {

    @Autowired
    private CardService cardService;
    @Autowired
    private RedisCacheManager cacheManager;

    @Test
    @Order(1)
    @DisplayName("캐시 x")
    void CardCacheOff() {
        cacheManager.getCache("allCard").clear();
        long startTime = System.currentTimeMillis();
        try {
            List<CardResponse> cacheOff = cardService.getCardsV2Cache();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("[Cache X] execution time:" + executionTime + " m/s ");
        }
    }

    @Test
    @Order(2)
    @DisplayName("캐시 o")
    void CardCacheOn() {
        long startTime = System.currentTimeMillis();
        try {
            List<CardResponse> cacheOn = cardService.getCardsV2Cache();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("[Cache O] execution time:" + executionTime + " m/s ");
        }
    }
}
