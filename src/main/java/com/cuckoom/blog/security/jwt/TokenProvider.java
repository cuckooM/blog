package com.cuckoom.blog.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
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
import lombok.RequiredArgsConstructor;

/**
 * JWT Provider
 * @author cuckooM
 */
@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

   /** 运行时日志 */
   private final static Logger log = LoggerFactory.getLogger(TokenProvider.class);

   /** 关键字：auth */
   private static final String CLAIM_AUTHORITIES = "auth";

   /** 关键字：userId */
   private static final String CLAIM_USERID = "userId";

   /** 关键字：displayName */
   private static final String CLAIM_DISPLAY_NAME = "displayName";

   /** JWT 配置参数 */
   private final JwtProperties jwtProperties;

   /** 密钥 */
   private Key key;

   @Override
   public void afterPropertiesSet() {
      this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretBase64()));
   }

   public String createToken(Authentication authentication, boolean rememberMe) {

      TokenUser tokenUser = (TokenUser) authentication.getPrincipal();

      String authorities = authentication.getAuthorities().stream()
         .map(GrantedAuthority::getAuthority)
         .collect(Collectors.joining(","));

      Long expiration = rememberMe ? this.jwtProperties.getExpiration() : this.jwtProperties.getExpirationRememberMe();
      Date validity = new Date(System.currentTimeMillis() + expiration * 1000);

      return Jwts.builder()
          .setSubject(authentication.getName())
          .claim(CLAIM_AUTHORITIES, authorities)
          .claim(CLAIM_USERID, tokenUser.getUserId())
          .claim(CLAIM_DISPLAY_NAME, tokenUser.getDisplayName())
          .signWith(key, SignatureAlgorithm.HS512)
          .setExpiration(validity)
          .compact();
   }

   public Authentication getAuthentication(String token) {
      Claims claims = Jwts.parserBuilder()
         .setSigningKey(key)
         .build()
         .parseClaimsJws(token)
         .getBody();

      Collection<? extends GrantedAuthority> authorities =
         Arrays.stream(claims.get(CLAIM_AUTHORITIES).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

      User principal = new TokenUser(Long.parseLong(claims.get(CLAIM_USERID).toString()), claims.getSubject(),
          claims.get(CLAIM_DISPLAY_NAME).toString(),"", authorities);

      return new UsernamePasswordAuthenticationToken(principal, token, authorities);
   }

   public boolean validateToken(String authToken) {
      try {
         Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
         return true;
      } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
         log.trace("Invalid JWT signature trace.", e);
      } catch (ExpiredJwtException e) {
         log.trace("Expired JWT token trace.", e);
      } catch (UnsupportedJwtException e) {
         log.trace("Unsupported JWT token trace.", e);
      } catch (IllegalArgumentException e) {
         log.trace("JWT token compact of handler are invalid trace.", e);
      }
      return false;
   }
}
