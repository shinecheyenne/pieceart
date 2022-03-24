package com.example.pieceart.security.filter;

import com.example.pieceart.security.util.JWTUtil;
import com.nimbusds.jose.util.IOUtils;
import com.nimbusds.jose.util.JSONObjectUtils;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.Collectors;


@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    private JWTUtil jwtUtil;
    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("------------ApiLoginFilter-----------");
        log.info("--attemptAuthentication--");

        InputStream inputStream = request.getInputStream();
        String raw = IOUtils.readInputStreamToString(inputStream);

        Map<String, Object> map = null;
        try {
            map = JSONObjectUtils.parse(raw);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String username = (String) map.get("email");
        String password = (String) map.get("password");

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, password);
        log.info("-----------authToken---------");
        log.info(authToken);
        log.info("credentials: "+ authToken.getCredentials());
        log.info("principal: "+ authToken.getPrincipal());
        log.info("authorities: "+ authToken.getAuthorities());

        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("-------------ApiLoginFilter------------");
        log.info("----successfulAuthentication");
        log.info("auth result: "+ authResult);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        log.info("context: " +context.getAuthentication().getPrincipal());

        String email = ((User)authResult.getPrincipal()).getUsername();
        String roles = authResult.getAuthorities().toString();
        log.info("roles "+ roles);
        log.info("email "+ email);

        String token = jwtUtil.generateToken(email, authResult.getAuthorities().stream().map(role->role.toString()).collect(Collectors.toSet()));
        log.info("token: "+token);

        Cookie myCookie = new Cookie("hello", "hello");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(60*60*24);
        myCookie.setPath("/");

        response.setContentType("application/json;charset=UTF-8");
        response.addCookie(myCookie);
        JSONObject json = new JSONObject();
        json.put("token", token);

        PrintWriter out = response.getWriter();
        out.print(json);

//        chain.doFilter(request, response);
    }
}
