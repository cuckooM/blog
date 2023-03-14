package com.cuckoom.blog.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.cuckoom.blog.security.TokenUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {

   private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

   private static final String AUTHORITIES_KEY = "auth";

   private static final String USERID = "userId";

   private static final String DISPLAY_NAME = "display_name";

   @Value("${jwt.base64-secret:ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=}")
   private String base64Secret;
   @Value("${jwt.token-validity-in-seconds:8640000}")
   private long tokenValidityInMilliseconds;
   @Value("${jwt.token-validity-in-seconds-for-remember-me:10800000}") 
   private long tokenValidityInMillisecondsForRememberMe;

   private Key key;

   @Override
   public void afterPropertiesSet() {
      byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
      this.key = Keys.hmacShaKeyFor(keyBytes);
   }

   public String createToken(Authentication authentication, boolean rememberMe) {

      TokenUser tokenUser = (TokenUser) ((UsernamePasswordAuthenticationToken) authentication).getPrincipal();

      String authorities = authentication.getAuthorities().stream()
         .map(GrantedAuthority::getAuthority)
         .collect(Collectors.joining(","));

      long now = (new Date()).getTime();
      Date validity;
      if (rememberMe) {
         validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
      } else {
         validity = new Date(now + this.tokenValidityInMilliseconds);
      }

      return Jwts.builder()
          .setSubject(authentication.getName())
          .claim(AUTHORITIES_KEY, authorities)
          .claim(USERID, tokenUser.getUserId())
          .claim(DISPLAY_NAME, tokenUser.getDisplayName())
          .signWith(key, SignatureAlgorithm.HS512)
          .setExpiration(validity)
          .compact();
   }

   public Authentication getAuthentication(String token) {
      Claims claims = Jwts.parser()
         .setSigningKey(key)
         .parseClaimsJws(token)
         .getBody();
      Object obj = claims.get(USERID);
      Object obj2 = claims.get(DISPLAY_NAME);

      Collection<? extends GrantedAuthority> authorities =
         Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

      User principal = new TokenUser(Long.parseLong(claims.get(USERID).toString()), claims.getSubject(),
          claims.get(DISPLAY_NAME).toString(),"", authorities);

      return new UsernamePasswordAuthenticationToken(principal, token, authorities);
   }

   public boolean validateToken(String authToken) {
      try {
         Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
         return true;
      } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
         log.info("Invalid JWT signature.");
         log.trace("Invalid JWT signature trace: {}", e);
      } catch (ExpiredJwtException e) {
         log.info("Expired JWT token.");
         log.trace("Expired JWT token trace: {}", e);
      } catch (UnsupportedJwtException e) {
         log.info("Unsupported JWT token.");
         log.trace("Unsupported JWT token trace: {}", e);
      } catch (IllegalArgumentException e) {
         log.info("JWT token compact of handler are invalid.");
         log.trace("JWT token compact of handler are invalid trace: {}", e);
      }
      return false;
   }
}
