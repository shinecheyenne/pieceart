package com.example.pieceart.works;

import com.example.pieceart.auction.AuctionRepository;
import com.example.pieceart.entity.Auction;
import com.example.pieceart.entity.Pieces;
import com.example.pieceart.entity.Works;
import com.example.pieceart.pieces.PiecesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksServiceImpl implements WorksService{
    private final WorksRepository worksRepository;
    private final PiecesRepository piecesRepository;
    private final AuctionRepository auctionRepository;

    @Transactional(readOnly = true)
    public List<WorksDTO> getAllWorks(){
        List<Works> works = worksRepository.findAll();
        List<WorksDTO> list = new ArrayList<>();

        works.forEach(w->{
            int sumOfPieces = 0;
            int currentPrice = 0;
            List<Pieces> pieces= piecesRepository.findPiecesByWorks(w.getId());
            if(pieces.size()!=0) sumOfPieces = piecesRepository.findSumOfPieces(w.getId());
            List<Auction> auctions = auctionRepository.findAuctionByWorks(w.getId());
            if(auctions.size()!=0) currentPrice = auctionRepository.findCurrentPrice(w.getId());
            WorksDTO worksDTO = entityToDto(w);
            worksDTO.setCurrentPrice(currentPrice);
            worksDTO.setSumOfPieces(sumOfPieces);

            list.add(worksDTO);
        });

        return list;
    }
}
