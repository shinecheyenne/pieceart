package com.example.pieceart.pieces;

import com.example.pieceart.auction.AuctionDTO;
import com.example.pieceart.entity.Pieces;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PiecesRepositoryTest {
    @Autowired
    PiecesRepository piecesRepository;

    @Test
    public void readPieces() {
//        ArrayList<Object[]> list = piecesRepository.getPiecesList("user1@test.com");
//
//        list.forEach(el -> {
//            Long id = Long.parseLong(String.valueOf(el[0]));
//            Long worksId = Long.parseLong(String.valueOf(el[2]));
//            String piecesPurchased = String.valueOf(el[3])=="null"? "0":String.valueOf(el[3]);
//            String piecesSum = String.valueOf(el[4])=="null"? "0":String.valueOf(el[4]);
//            String initialPrice = String.valueOf(el[6])=="null"? "0":String.valueOf(el[6]);
//
//
//            PiecesDTO piecesDTO = PiecesDTO.builder()
//                    .id(id)
//                    .worksId(worksId)
//                    .purchaseDate(new java.sql.Date(((Date)el[1]).getTime()).toLocalDate())
//                    .piecesPurchased(Integer.parseInt(piecesPurchased))
//                    .piecesLeft(1000-Integer.parseInt(piecesSum))
//                    .worksTitle((String)el[5])
//                    .initialPrice(Integer.parseInt(initialPrice))
//                    .auctionEndDate(new java.sql.Date(((Date)el[7]).getTime()).toLocalDate())
//                    .imgUrl((String)el[8])
//                    .artistName((String)el[9])
//                    .build();
//
//            System.out.println(piecesDTO);
//        });
    }
    @Test
    public void readPiecesSum(){
    List<Pieces> pieces = piecesRepository.findPiecesByWorks((long)8);
    if(pieces.size()!=0){
        int sumOfPieces = piecesRepository.findSumOfPieces((long)8);
        System.out.println(sumOfPieces);
    }
    System.out.println("no result");
    }
}