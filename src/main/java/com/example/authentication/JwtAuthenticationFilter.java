package com.example.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    @Autowired
    private JwtUtill jwtUtill;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    String username=null;
    String jwtToken=null;

    @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("request token header call");
        System.out.println(requestTokenHeader);



        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {

                username = jwtUtill.extractUsername(jwtToken);
                System.out.println("request token header call with if with try after "+username);
            }catch (Exception e){
                e.printStackTrace();
            }
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                System.out.println("request token header call before validation check");
                    if(jwtUtill.validateToken(jwtToken,userDetails)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }else {
                        System.out.println("Token is not valid");
                    }

            }else {
                System.out.println("Token is not valid or authentication context is already set.");
            }

        }
        else {
            System.out.println("jwt token does not begin with bearer");
        }

        filterChain.doFilter(request,response);
   }
}
