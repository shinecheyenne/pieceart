package com.example.pieceart.promotion;

import com.example.pieceart.entity.Promotion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PromotionRepositoryTest {
    @Autowired
    private PromotionRepository repository;

    @Transactional
    @Test
    public void promotionTest(){
        Iterable<Promotion> list= repository.findAll();
        list.forEach(li -> {
            PromotionDTO promotionDTO = PromotionDTO.builder()
                    .id(li.getId())
                    .eventTitle(li.getEventTitle())
                    .eventDescription(li.getEventDescription())
                    .works(li.getWorks())
                    .build();
            System.out.println(promotionDTO);
        });
    }
}