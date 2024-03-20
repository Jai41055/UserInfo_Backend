package com.employee.userInfo.ServiceImpl;

import com.employee.userInfo.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    String SECRET_KEY = "2bd8038b9a5fa6dafdb099fea1f0000c7e8810e2285fcbf34a2ef59d72ab764a";

    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            User u = new User();
            u.setUserName(body.getSubject());
            u.setId(Integer.parseInt((String) body.get("id")));
            u.setEmployeeNumber((String) body.get("number"));
            u.setEmployeeMobile((String) body.get("mobile"));
            u.setEmployeeEmail((String) body.get("mail"));
            return u;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public boolean isValid(String token, UserDetails user){
        User u = parseToken(token);
        return user.getUsername().equals(u.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody();

    }

    public String generateToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("number", user.getEmployeeNumber());
        claims.put("mobile", user.getEmployeeMobile());
        claims.put("mail", user.getEmployeeEmail());
        claims.put("id", user.getId());
        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
