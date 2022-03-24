package com.example.pieceart.promotion;

import com.example.pieceart.entity.Promotion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService {
    private PromotionRepository promotionRepository;

    public List<PromotionDTO> findAllPromotion(){
        return null;
//        List<Promotion> promotions = promotionRepository.findAll();
//        List<PromotionDTO> list = new ArrayList<>();
//
//        list.forEach(li -> list.add(entityToDto(li)));
//        return list;
    }
}
