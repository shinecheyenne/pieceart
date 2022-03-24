//package com.example.pieceart.security;
//
//import com.example.pieceart.security.util.JWTUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class JWTTests {
//    private JWTUtil jwtUtil;
//
//    @BeforeEach
//    public void testBefore(){
//        System.out.println("testBefore............");
//        jwtUtil = new JWTUtil();
//    }
//
//    @Test
//    public void testEncode() throws Exception{
//        String email = "user96@test.com";
//        String str = jwtUtil.generateToken(email);
//        System.out.println("1"+str);
//    }
//    @Test
//    public void testValidate() throws Exception{
//        String email = "user96@test.com";
//        String str= jwtUtil.generateToken(email);
//        Thread.sleep(5000);
//        String resultEmail = jwtUtil.validateAndExtract(str);
//        System.out.println("2"+resultEmail);
//    }
//}
