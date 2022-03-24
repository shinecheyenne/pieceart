package com.example.pieceart.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String password="1111";
        String enPw = passwordEncoder.encode(password);
        System.out.println(enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);
        System.out.println(matchResult);

    }
}
