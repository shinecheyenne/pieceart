package com.example.pieceart.member;

import com.example.pieceart.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("user"+i+"@test.com")
                    .name("user"+i)
                    .password("1234")
                    .build();
            memberRepository.save(member);
        });
    }
}