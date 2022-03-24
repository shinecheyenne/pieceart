package com.example.pieceart.notice;

import com.example.pieceart.entity.File;
import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.Notice;
import com.example.pieceart.file.FileRepository;
import com.example.pieceart.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class NoticeRepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FileRepository fileRepository;

    @Commit
    @Transactional
    @Test
    public void insertNotice(){
        IntStream.rangeClosed(1, 34).forEach(i->{
            Member member = memberRepository.findByEmail("user20@test.com", false).get();
            Notice notice = Notice.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .password("1234")
                    .member(member)
                    .build();
            noticeRepository.save(notice);

//            int count = (int)(Math.random()*2)+1;

//            for(int j=0;j<count;j++){
//                File file = File.builder().fileName("test"+j+".png").notice(notice).build();
//                fileRepository.save(file);
//            }
        });
    }
    @Transactional
    @Test
    public void testRead(){

    }

//    @Test
//    public void testReadwithUser(){
//        List<Object[]> result = noticeRepository.getNoticeswithUser("user100@test.com");
//        for(Object[] arr: result){
//            System.out.println(Arrays.toString(arr));
//        }
//    }
}