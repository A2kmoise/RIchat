package com.example.rib.Iconf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.function.Function;

public class TokenGenerator {
    private final  String SECRET_KEY = "SECRETfirst";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date getExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver ){
        final Claims claims = extractAllClaims(token);
        return  resolver.apply(claims);
    }

   public String tokenGenerate(String username){
         /*return JWTS*/
   return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 *60))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
}

public boolean isTokenValid(String token, String username){
        final String extractUsername = extractUsername(token);
        return (extractUsername.equals(username) && !isTokenExpired(token));
}

public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
}

private Claims extractAllClaims(String token){
        return  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

}
}
