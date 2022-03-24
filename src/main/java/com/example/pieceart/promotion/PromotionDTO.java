package com.example.pieceart.promotion;

import com.example.pieceart.entity.Works;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PromotionDTO {
    private Long id;
    private String eventTitle;
    private String eventDescription;
    private Works works;
}
