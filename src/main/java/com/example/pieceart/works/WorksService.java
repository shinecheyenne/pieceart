package com.example.pieceart.works;
import com.example.pieceart.entity.Works;

import java.util.List;

public interface WorksService {
    List<WorksDTO> getAllWorks();

    default WorksDTO entityToDto(Works works){
        WorksDTO worksDTO = WorksDTO.builder()
                .id(works.getId())
                .name(works.getName())
                .description(works.getDescription())
                .size(works.getSize())
                .createdYear(works.getCreatedYear())
                .auctionStartDate(works.getAuctionStartDate())
                .auctionEndDate(works.getAuctionEndDate())
                .initialPrice(works.getInitialPrice())
                .artist(works.getArtist())
                .image(works.getImages())
                .build();

        return worksDTO;
    }
}
