package com.example.pieceart.member;

import com.example.pieceart.security.dto.MemberDTO;
import com.example.pieceart.security.service.MemberUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberUserDetailsService service;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> join(@RequestBody MemberDTO member) throws SQLException {
        log.info("member: "+member);
        boolean result = service.saveMember(member);

        Map<String, Object> map = new HashMap<>();
        map.put("status", result);

        if(!result) return ResponseEntity.badRequest().body(map);
        return ResponseEntity.ok().body(map);
    }

    @PutMapping("users/edit")
    public ResponseEntity<Map<String, Object>> edit(@RequestBody MemberDTO member, Authentication authentication, HttpServletResponse response) throws IOException {
        String result = service.editMember(member, authentication, response);

        Map<String, Object> map = new HashMap<>();
        map.put("token", result);
        if(result==null) return ResponseEntity.badRequest().body(map);
        else return ResponseEntity.ok().body(map);

    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<Map<String, Object>> delete(Authentication authentication){
        boolean result = service.deleteMember(authentication);
        if(result) return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDTO member){
//        log.info("member_login: "+member);
//        String result = service.sendAuthToken(member);
//        Map<String, Object> map = new HashMap<>();
//
//        if(result==null){} //예외처리
//
//        map.put("token", result);
//        return ResponseEntity.ok().body(map);
//    }
}
