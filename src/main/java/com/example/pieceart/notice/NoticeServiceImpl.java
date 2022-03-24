package com.example.pieceart.notice;

import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.Notice;
import com.example.pieceart.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository repository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NoticeDTO> findByPage(int page){
        Iterable<Notice> notices = repository.findAll(PageRequest.of(page-1, 10, Sort.by("id").descending()));
        List<NoticeDTO> list = new ArrayList<>();
        notices.forEach(notice -> {
            list.add(entityToDto(notice));
        });
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeDTO> findAll(){
        Iterable<Notice> notices = repository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<NoticeDTO> list = new ArrayList<>();
        notices.forEach(notice -> {
            list.add(entityToDto(notice));
        });
        return list;
    }

    @Override
    @Transactional
    public NoticeDTO findById(Long id){
        Optional<Notice> notice = repository.findById(id);
        if(notice.isPresent()){
            Notice increaseNotice = notice.get();
            increaseNotice.increaseViewCount(increaseNotice.getViewCount()+1);
            return entityToDto(repository.save(increaseNotice));
        }
        return null;
    }

    @Override
    @Transactional
    public NoticeDTO create(NoticeDTO noticeDTO){
        Member member = memberRepository.findByEmail(noticeDTO.getMemberEmail(), false).get();
        Notice created  = repository.save(dtoToEntity(noticeDTO, member));
        return entityToDto(created);

    }
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public NoticeDTO update(Long id, NoticeDTO noticeDTO){
        return repository.findById(id)
                .map(notice->{
                    notice.setTitle(noticeDTO.getTitle());
                    notice.setContent(noticeDTO.getContent());
                    return entityToDto(repository.save(notice));
                }).get();
    }
}
