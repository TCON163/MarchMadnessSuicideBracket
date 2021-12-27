package com.tcontechco.Tourney.filters;
import com.tcontechco.Tourney.exceptions.AuthenticationException;
import com.tcontechco.Tourney.exceptions.MalformedTokenException;
import com.tcontechco.Tourney.exceptions.MissingTokenException;
import com.tcontechco.Tourney.services.PlayerService;
import com.tcontechco.Tourney.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter @Setter
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private PlayerService playerService;
    private JWTUtil jwtUtil;


    @Autowired
    public AuthenticationFilter(PlayerService playerService, JWTUtil jwtUtil){
        this.playerService = playerService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationFilter(){
        playerService = null;
        jwtUtil = null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException{
        if(playerService == null && jwtUtil == null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            if(webApplicationContext != null){
                jwtUtil = webApplicationContext.getBean(JWTUtil.class);
                playerService = webApplicationContext.getBean(PlayerService.class);
            }
        }

        if (!request.getRequestURI().equals("/api/v1/login") && !request.getRequestURI().equals("/api/v1/register")){
            try{
                parseToken(request);
            }catch (AuthenticationException e){
                e.printStackTrace();
            }
        }

        chain.doFilter(request,response);
    }



    private void parseToken(ServletRequest request) throws AuthenticationException{
        String username;
        String jwt;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String tokenWithPrefix = httpRequest.getHeader(jwtUtil.getHeader().toLowerCase());

        if(tokenWithPrefix != null && (tokenWithPrefix.startsWith(jwtUtil.getPrefix())|| tokenWithPrefix.startsWith(jwtUtil.getPrefix().toLowerCase()))){
            jwt = tokenWithPrefix.substring(jwtUtil.getPrefix().length());

            try {
                jwtUtil.validateToken(jwt);
                username = jwtUtil.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                throw new AuthenticationException("This JWT is not valid.");
            } catch (ExpiredJwtException e) {
                throw new AuthenticationException("JWT Token has expired.");
            } catch (Exception e) {
                throw new AuthenticationException("This token has expired.");
            }
        } else {
            throw new AuthenticationException("Unauthrized prefix detected! Denied.");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = playerService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                upaToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(httpRequest));

                SecurityContextHolder.getContext().setAuthentication(upaToken);
            }
        }
    }

}
