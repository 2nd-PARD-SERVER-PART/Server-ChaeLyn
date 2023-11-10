package com.pard.hwSecurity.filter;

import com.pard.hwSecurity.security.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private TokenProvider tokenProvider;
    @Autowired
    public JwtAuthenticationFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{
        String token = parseBearerToken(request);

        try{
            if(token != null && !token.equalsIgnoreCase("null")){ //토큰에 값이 있다면
                String userEmail = tokenProvider.validate(token); //유효성 검사를 합니당

                AbstractAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                //맞다면 UsernamePasswordAuthenticationToken 이거를 줌, 이메일 정보있고, 비번 비어있고
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); //여기에 인증정보 담아둘거임 일단 비어있긔
                securityContext.setAuthentication(authenticationToken); //위에서 만든 토큰으로 authentication 객체 만들고, 인증정보 저장함

                SecurityContextHolder.setContext(securityContext); //스레드에서 계속하여 사용자 정보 알 수 있게 함
            }
        }catch (Exception e){
            e.printStackTrace(); //토큰이 유요하지 않다던지,,,
        }
        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Autorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) //비어있지 않은지, "Bearer "로 시작하는지 확인
            return bearerToken.substring(7); // "Bearer " 이부분 건너뛰고 7번째부터 확인하게 넘겨줌
        return null; //조건에 맞지 않다면 null return
    }
}
