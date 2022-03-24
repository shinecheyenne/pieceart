package com.example.pieceart.auction;

import com.example.pieceart.entity.Auction;
import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.Works;
import com.example.pieceart.member.MemberRepository;
import com.example.pieceart.works.WorksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuctionRepositoryTest {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WorksRepository worksRepository;

    @Test
    public void insertAuctionTest(){
        IntStream.rangeClosed(1, 14).forEach(i->{
            Member member = memberRepository.findByEmail("user1@test.com", false).get();
            Works works = worksRepository.findById((long)i).get();

            Auction auction = Auction.builder().currentPrice(i+1000000).member(member).works(works).bidDate(LocalDateTime.now()).build();

            auctionRepository.save(auction);
        });

//        IntStream.rangeClosed(1, 5).forEach(i->{
//            Member member = memberRepository.findByEmail("user1@test.com", false).get();
//            Works works = worksRepository.findById((long)1).get();
//
//            Auction auction = Auction.builder().currentPrice(i+3000000).member(member).works(works).bidDate(LocalDateTime.now()).build();
//
//            auctionRepository.save(auction);
//        });
    }

//    @Test
//    public void readAuctions(){
//        ArrayList<Object[]> list = auctionRepository.findAuctionByUser("user1@test.com");
//
//        list.forEach(el -> {
//            Long id = Long.parseLong(String.valueOf(el[0]));
//            Long worksId = Long.parseLong(String.valueOf(el[1]));
//            String myPrice = String.valueOf(el[7])=="null"? "0":String.valueOf(el[7]);
//            String currentPrice = String.valueOf(el[8])=="null"?"0":String.valueOf(el[8]);
//            AuctionDTO auctionDTO = AuctionDTO.builder()
//                    .id(id)
//                    .worksId(worksId)
//                    .worksTitle((String)el[2])
//                    .auctionEndDate(new java.sql.Date(((Date)el[3]).getTime()).toLocalDate())
//                    .imgUrl((String)el[4])
//                    .artistName((String) el[5])
//                    .bidDate(new java.sql.Timestamp(((Date)el[6]).getTime()).toLocalDateTime())
//                    .myPrice(Integer.parseInt(myPrice))
//                    .currentPrice(Integer.parseInt(currentPrice))
//                    .build();
//
//            System.out.println(auctionDTO);
//
//        });
//    }
    @Test
    public void createAuctionTest(){
//        Member member = memberRepository.findByEmail("user1@test.com", false).get();
//        Works works = worksRepository.findById((long)1).get();
//        Auction auction = Auction.builder().member(member).works(works).currentPrice(500000000).build();
        System.out.println(auctionRepository.findCurrentPrice((long)1));
//        auctionRepository.save(auction);
    }

}