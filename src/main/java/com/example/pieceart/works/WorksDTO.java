package com.example.pieceart.works;

import com.example.pieceart.entity.Artist;
import com.example.pieceart.entity.Image;
import com.example.pieceart.entity.Pieces;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorksDTO {
    private Long id;
    private String name;
    private String description;
    private String size;
    private String createdYear;
    private LocalDate auctionStartDate;
    private LocalDate auctionEndDate;
    private int initialPrice;
    private int currentPrice;

    private Artist artist;
    private Set<Image> image;
    private int sumOfPieces;
}
