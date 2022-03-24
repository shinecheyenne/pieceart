package com.example.pieceart.auction;

import com.example.pieceart.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    @Query(value="SELECT max(a.currentPrice) from Auction a where a.works.id=:worksId")
    int findCurrentPrice(@Param("worksId") Long worksId);

    @Query(value="SELECT a from Auction a WHERE a.works.id=:worksId")
    List<Auction> findAuctionByWorks(@Param("worksId") Long worksId);

    @Query(value="SELECT a from Auction a WHERE a.member.email=:email")
    List<Auction> findAuctionByUser(@Param("email") String email);

//    @Query(value = "SELECT DISTINCT auction.id, w.id as works_id, w.name as works_title, w.auction_end_date," +
//            " i.image_url, a.name as artist_name, auction.bid_date, auction.current_price as my_price, p.current_price" +
//            " from auction, member, works as w, works_image as i, artist as a," +
//            " (select works_id, max(current_price) as current_price from auction group by works_id) as p" +
//            " where auction.works_id=w.id" +
//            " and auction.member_id = (select id from Member where email = :email)" +
//            " and auction.works_id=i.works_id" +
//            " and i.type='ma'" +
//            " and w.artist_id=a.id" +
//            " and p.works_id=auction.works_id" +
//            " order by works_id, bid_date desc"
//            , nativeQuery = true
//    )
//    ArrayList<Object[]> getAuctionList(@Param("email") String email);

//    @Query(value="SELECT max(current_price) from auction where works_id=:worksId", nativeQuery = true)
//    int findCurrentPrice(@Param("worksId") Long worksId);
}

