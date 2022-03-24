package com.example.pieceart.notice;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor //@RequestBody로 Java 객체로 변환 될 때 직렬화를 위해 기본생성자는 필수
public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDate created;
    private LocalDate modified;
    private String password;
    private int viewCount;
    private Long fileId;
    private String memberEmail;
    private String memberName;
}

