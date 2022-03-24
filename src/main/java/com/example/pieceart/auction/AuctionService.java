package com.example.pieceart.auction;

import com.example.pieceart.entity.Auction;

import java.util.List;
import java.util.stream.Collectors;

public interface AuctionService {
    List<AuctionDTO> findAuctionByUser(String email);
    boolean createAuction(String email, Long worksId, int currentPrice);
    boolean deleteAuction(String email, Long auctionId);

    default AuctionDTO entityToDto(Auction auction){
        AuctionDTO auctionDTO = AuctionDTO.builder()
                .id(auction.getId())
                .myPrice(auction.getCurrentPrice())
                .bidDate(auction.getBidDate())
                .worksId(auction.getWorks().getId())
                .worksTitle(auction.getWorks().getName())
                .auctionEndDate(auction.getWorks().getAuctionEndDate())
                .imgUrl(auction.getWorks().getImages().stream().filter(i->i.getType().equals("ma")).collect(Collectors.toList()).get(0))
                .artistName(auction.getWorks().getArtist().getName())
                .build();
        return auctionDTO;
    }

//    default ArrayList<AuctionDTO> objectToDto(ArrayList<Object[]> auctions){
//        ArrayList<AuctionDTO> auctionList = new ArrayList<>();
//
//        auctions.forEach(auction->{
//            Long id = Long.parseLong(String.valueOf(auction[0]));
//            Long worksId = Long.parseLong(String.valueOf(auction[1]));
//            String myPrice = String.valueOf(auction[7])=="null"? "0":String.valueOf(auction[7]);
//            String currentPrice = String.valueOf(auction[8])=="null"?"0":String.valueOf(auction[8]);
//            AuctionDTO auctionDTO = AuctionDTO.builder()
//                    .id(id)
//                    .worksId(worksId)
//                    .worksTitle((String)auction[2])
//                    .auctionEndDate(new java.sql.Date(((Date)auction[3]).getTime()).toLocalDate())
//                    .imgUrl((String)auction[4])
//                    .artistName((String) auction[5])
//                    .bidDate(new java.sql.Timestamp(((Date)auction[6]).getTime()).toLocalDateTime())
//                    .myPrice(Integer.parseInt(myPrice))
//                    .currentPrice(Integer.parseInt(currentPrice))
//                    .build();
//
//            auctionList.add(auctionDTO);
//        });
//        return auctionList;
//    }
}
