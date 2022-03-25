package com.example.pieceart.security.service;

import com.example.pieceart.entity.Member;
import com.example.pieceart.entity.MemberRole;
import com.example.pieceart.member.MemberRepository;
import com.example.pieceart.security.dto.MemberDTO;
import com.example.pieceart.security.util.JWTUtil;
import com.nimbusds.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberDetailsService loadUserByUsername "+ username);
        Optional<Member> mem = memberRepository.findByEmail(username);
        log.info(mem.get());
        if(!mem.isPresent()) throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");

        Member member = mem.get();
        log.info(member.getRoleSet().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet()));

        log.info(new User(member.getEmail(), member.getPassword(), member.getRoleSet().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet())));

        return new User(member.getEmail(), member.getPassword(), member.getRoleSet().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet()));
    }

    @Transactional
    public boolean saveMember(MemberDTO memberDTO) throws SQLException {
        log.info("-----------saveMEmber-------------");
        log.info(memberDTO.getEmail());
        Optional<Member> mem = memberRepository.findByEmail(memberDTO.getEmail(), false);
        if(mem.isPresent()){
            return false;
        }
        log.info("memberDTO: "+memberDTO.getName());
        if(memberRepository.findByName(memberDTO.getName()).isPresent()){
            throw new SQLException("닉네임 중복"); //view alert하기
        }
        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .name(memberDTO.getName())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .isGoogle(false)
                .build();
        member.addMemberRole(MemberRole.USER);
        member.addMemberRole(MemberRole.ADMIN);
        memberRepository.save(member);
        return true;
    }

    @Transactional
    public String editMember(MemberDTO memberDTO, Authentication authentication) throws IOException {
        String email = authentication.getPrincipal().toString();
        Optional<Member> mem = memberRepository.findByEmail(email, false);
        if(!mem.isPresent()) return null;

        memberRepository.findByEmail(email, false)
                .map(member->{
                    Member newMember = Member.builder()
                            .id(member.getId())
                            .isGoogle(member.isGoogle())
                            .email(member.getEmail())
                            .name(member.getName())
                            .createdDate(member.getCreatedDate())
                            .password(passwordEncoder.encode(memberDTO.getPassword()))
                            .build();
                    newMember.addMemberRole(MemberRole.USER);
                    return memberRepository.save(newMember);
                });
        String token = jwtUtil.generateToken(email, authentication.getAuthorities().stream().map(role->role.toString()).collect(Collectors.toSet()));
        log.info("token: "+token);

        return token;
    }

    @Transactional
    public boolean deleteMember(Authentication authentication){
        String email = authentication.getPrincipal().toString();

        Optional<Member> member = memberRepository.findByEmail(email, false);
        if(member.isPresent()) {
            memberRepository.deleteById(member.get().getId());
            return true;
        }
        return false;

    }
}
