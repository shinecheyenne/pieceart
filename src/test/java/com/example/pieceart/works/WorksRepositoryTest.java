package com.example.pieceart.works;

import com.example.pieceart.entity.Pieces;
import com.example.pieceart.entity.Works;
import com.example.pieceart.pieces.PiecesDTO;
import com.example.pieceart.pieces.PiecesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorksRepositoryTest {
    @Autowired
    private WorksRepository worksRepository;
    @Autowired
    private PiecesRepository piecesRepository;

    @Transactional
    @Test
    public void worksTest(){
        Iterable<Works> works = worksRepository.findAll();
        List<WorksDTO> list = new ArrayList<>();
        works.forEach(w->{
            int sumOfPieces = 0;
            List<Pieces> pieces= piecesRepository.findPiecesByWorks(w.getId());
            if(pieces.size()!=0) sumOfPieces = piecesRepository.findSumOfPieces(w.getId());

            WorksDTO worksDTO = WorksDTO.builder()
                    .id(w.getId())
                    .name(w.getName())
                    .description(w.getDescription())
                    .size(w.getSize())
                    .createdYear(w.getCreatedYear())
                    .auctionStartDate(w.getAuctionStartDate())
                    .auctionEndDate(w.getAuctionEndDate())
                    .initialPrice(w.getInitialPrice())
                    .artist(w.getArtist())
                    .sumOfPieces(sumOfPieces)
                    .build();

            System.out.println(worksDTO);
        });
    }

}