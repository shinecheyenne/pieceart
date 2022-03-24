package com.example.pieceart.wishlist;

import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.Pieces;
import com.example.pieceart.entity.Wishlist;
import com.example.pieceart.entity.Works;
import com.example.pieceart.member.MemberRepository;
import com.example.pieceart.pieces.PiecesRepository;
import com.example.pieceart.works.WorksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class WishlistRepositoryTest {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WorksRepository worksRepository;

    @Autowired
    PiecesRepository piecesRepository;

    @Test
    public void insertWishlistTest(){
        IntStream.rangeClosed(1,16).forEach(i->{
//            Long artworkId = (long)(Math.random()*16)+1;
//            Long memberId = (long)(Math.random()*19)+1;

            Member member = memberRepository.findByEmail("user1@test.com", false).get();
            Works works = worksRepository.findById((long)i).get();

            Wishlist wishlist = Wishlist.builder().member(member).works(works).build();

            wishlistRepository.save(wishlist);
        });

    }
    @Transactional
    @Test
    public void readWish(){
        List<Wishlist> wishes = wishlistRepository.getWorksInWishList("user1@test.com");
        for (Wishlist wish : wishes){
            System.out.println(wish);
        }
    }
//    @Transactional
//    @Test
//    public void readWish2(){
//        ArrayList<Object[]> wishes2 = wishlistRepository.getWishlistByMember("user1@test.com");
//
//        wishes2.forEach(wish-> {
//            Long id = Long.parseLong(String.valueOf(wish[0]));
//            String pieces = String.valueOf(wish[4])=="null"? "0":String.valueOf(wish[4]);
//            String price = String.valueOf(wish[5])=="null"?"0":String.valueOf(wish[5]);
//            System.out.println(price);
//            WishlistDTO wishlistDTO = WishlistDTO.builder()
//                    .id(id)
//                    .artist_name((String) wish[1])
//                    .works_title((String) wish[2])
//                    .img_url((String) wish[3])
//                    .pieces_left(1000-Integer.parseInt(pieces))
//                    .current_price(Integer.parseInt(price))
//                    .build();
//            System.out.println(wishlistDTO);
//            }
//        );
//    }
//
//    @Transactional
//    @Test
//    public void readWish3(){
//        ArrayList<Object[]> wishes3 = wishlistRepository.getWishlistById((long)1, "user1@test.com");
//
//        wishes3.forEach(wish-> {
//                    Long id = Long.parseLong(String.valueOf(wish[0]));
//                    String pieces = String.valueOf(wish[4])=="null"? "0":String.valueOf(wish[4]);
//                    String price = String.valueOf(wish[5])=="null"?"0":String.valueOf(wish[5]);
//                    System.out.println(price);
//                    WishlistDTO wishlistDTO = WishlistDTO.builder()
//                            .id(id)
//                            .artist_name((String) wish[1])
//                            .works_title((String) wish[2])
//                            .img_url((String) wish[3])
//                            .pieces_left(1000-Integer.parseInt(pieces))
//                            .current_price(Integer.parseInt(price))
//                            .build();
//                    System.out.println(wishlistDTO);
//                }
//        );
//    }

    @Transactional
    @Test
    public void readWish4(){
        List<Wishlist> wishlist = wishlistRepository.findAll();
        wishlist.forEach(w->{
            int piecesLeft = 0;
            List<Pieces> pieces = piecesRepository.findPiecesByWorks(w.getId());
            if(pieces.size()!=0) piecesLeft = piecesRepository.findSumOfPieces(w.getId());

            WishlistDTO wishlistDTO = WishlistDTO.builder()
                    .id(w.getId())
                    .works_title(w.getWorks().getName())
                    .initial_price(w.getWorks().getInitialPrice())
                    .artist_name(w.getWorks().getArtist().getName())
                    .img_url(w.getWorks().getImages().stream().filter(i->i.getType().equals("ma")).collect(Collectors.toList()).get(0))
                    .pieces_left(piecesLeft)
                    .build();
            System.out.println(wishlistDTO);
        });

    }

}