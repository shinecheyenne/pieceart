package com.example.pieceart.wishlist;

import com.example.pieceart.entity.Wishlist;

import java.util.List;
import java.util.stream.Collectors;

public interface WishlistService {
    List<WishlistDTO> getWishList(String email);
    boolean createWishlist(String email, Long worksId);
    void deleteWishList(Long wishlistId);

    default WishlistDTO entityToDto(Wishlist wishlist){
        WishlistDTO wishlistDTO = WishlistDTO.builder()
                .id(wishlist.getId())
                .works_title(wishlist.getWorks().getName())
                .initial_price(wishlist.getWorks().getInitialPrice())
                .artist_name(wishlist.getWorks().getArtist().getName())
                .img_url(wishlist.getWorks().getImages().stream().filter(i->i.getType().equals("ma")).collect(Collectors.toList()).get(0))
                .build();
        return wishlistDTO;
    }
}
