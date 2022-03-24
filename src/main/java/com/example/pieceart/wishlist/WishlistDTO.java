package com.example.pieceart.wishlist;

import com.example.pieceart.entity.Image;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WishlistDTO {
    private Long id;

    private String works_title;
    private int initial_price;

    private String artist_name;
    private int pieces_left;
    private int current_price;
    private Image img_url;
}
