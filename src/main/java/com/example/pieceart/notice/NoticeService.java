package com.example.pieceart.notice;

import com.example.pieceart.entity.Notice;
import com.example.pieceart.entity.Member;

import java.util.List;

public interface NoticeService {

    List<NoticeDTO> findByPage(int page);
    List<NoticeDTO> findAll();
    NoticeDTO findById(Long id);
    NoticeDTO create(NoticeDTO noticeDTO);
    void delete(Long id);
    NoticeDTO update(Long id, NoticeDTO noticeDTO);

    default NoticeDTO entityToDto (Notice notice){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .id(notice.getId())
                .content(notice.getContent())
                .title(notice.getTitle())
                .password(notice.getPassword())
                .viewCount(notice.getViewCount())
                .created(notice.getCreated())
                .modified(notice.getModified())
                .memberEmail(notice.getMember().getEmail())
                .memberName(notice.getMember().getName())
                .build();
        return noticeDTO;
    }

    default Notice dtoToEntity (NoticeDTO noticeDTO, Member member){
        Notice notice = Notice.builder()
                .id(noticeDTO.getId())
                .password(noticeDTO.getPassword())
                .content(noticeDTO.getContent())
                .viewCount(noticeDTO.getViewCount())
                .title(noticeDTO.getTitle())
                .member(member)
                .build();
        return notice;
    }
}
