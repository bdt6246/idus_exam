package com.example.idus_exam.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        System.out.println("LoginFilter 실행됐다.");
//        UsernamePasswordAuthenticationToken authToken;
//        try {
//            MemberDto.SignupRequest MemberDto  = new ObjectMapper().readValue(request.getInputStream(), MemberDto.SignupRequest.class);
//            authToken =
//                    new UsernamePasswordAuthenticationToken(MemberDto.getEmail(), MemberDto.getPassword(), null);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return authenticationManager.authenticate(authToken);
//    }


//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        Member Member = (Member) authResult.getPrincipal();
//        String jwtToken = JwtUtil.generateToken(Member.getIdx(), Member.getEmail(), Member.getNickName(), Member.getRole());
//        ResponseCookie cookie = ResponseCookie
//                .from("ATOKEN", jwtToken)
//                .path("/")
//                .httpOnly(true)
//                .secure(true)
//                .maxAge(Duration.ofHours(1L))
//                .build();
//
//        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//    }
}
