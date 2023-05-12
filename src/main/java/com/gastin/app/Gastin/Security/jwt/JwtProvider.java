package com.gastin.app.Gastin.Security.jwt;

import com.gastin.app.Gastin.Security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getAlias()).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ expiration * 180))
                .signWith(getSecret(secret)).compact();
    }
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJwt(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJwt(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }
        catch (ExpiredJwtException e){
            logger.error("token expirado");
        }
        catch (IllegalArgumentException e){
            logger.error("token vacio");
        }
        catch (SignatureException e){
            logger.error("fail en la firma");
        }
        return false;
    }

    private Key getSecret(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
