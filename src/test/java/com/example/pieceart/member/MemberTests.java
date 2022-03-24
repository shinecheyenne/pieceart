package com.example.pieceart.member;

import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {
    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 20).forEach(i->{
            Member member = Member.builder().email("user"+i+"@test.com")
                    .isGoogle(false)
                    .name("user"+i)
                    .password(passwordEncoder.encode("llllllll"))
                    .build();
            member.addMemberRole(MemberRole.USER);
            if(i==20){
                member.addMemberRole(MemberRole.ADMIN);
            }
            if(i==19){
                member.addMemberRole(MemberRole.MANAGER);
            }

            repository.save(member);
        });
    }

    @Test
    public void testRead(){
        Optional<Member> result = repository.findByEmail("user95@test.com", false);
        Member member = result.get();
        System.out.println(member);
    }
}
