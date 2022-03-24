//package com.example.pieceart.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jws;
//import lombok.extern.log4j.Log4j2;
//import net.minidev.json.JSONObject;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Log4j2
//public class JwtExceptionFilter extends OncePerRequestFilter {
//    private JWTUtil jwtUtil;
//    public JwtExceptionFilter(JWTUtil jwtUtil){
//        this.jwtUtil = jwtUtil;
//    }
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("--------------jwtExceptionFilter---------------");
//        String token = jwtUtil.extractTokenFromHeader(request);
//        try{
//            log.info("----try----");
//            Jws<Claims> claims = jwtUtil.getClaimsFromToken(token);
//            log.info(claims);
//            filterChain.doFilter(request, response);
//            return;
//        } catch(ExpiredJwtException e){
//            log.info("-----catch----");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json;charset=UTF-8");
//            JSONObject json = new JSONObject();
//            String message = "EXPIRED TOKEN RECEIVED";
//            json.put("code", "401");
//            json.put("message", message);
//
//            PrintWriter out = response.getWriter();
//            out.print(json);
//            return;
//        }
//    }
//}
