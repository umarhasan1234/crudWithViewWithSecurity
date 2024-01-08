package com.example.authentication;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtUtill {

    private static final long EXPIRATION_TIME = 864000000;//10 days
   private String SECRET_KEY="secret";

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {


        try {
         //   System.out.println("Token received: " + token);
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        //    System.out.println("Claims extracted successfully: " + claims);
            return claims;
        } catch (Exception e) {
            System.err.println("Error extracting claims from token: " + e.getMessage());
            e.printStackTrace();
            return null; // Handle the exception appropriately in your code
        }
//        System.out.println("get body "+token);
//        Claims claims=Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//        System.out.println("claim is "+claims);
      //        return claims;
      //  return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

    }

//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//public void logTokenExpirationDetails(String token) {
//    Date expirationDate = extractExpiration(token);
//    Date currentTime = new Date();
//
//    System.out.println("Current Time: " + currentTime);
//    System.out.println("Token Expiration Time: " + expirationDate);
//
//    long timeRemaining = expirationDate.getTime() - currentTime.getTime();
//    System.out.println("Time Remaining (milliseconds): " + timeRemaining);
//    System.out.println("Time Remaining (minutes): " + (timeRemaining /(60*1000)));
//}


    private Boolean isTokenExpired(String token) {
        Date expirationDate = extractExpiration(token);
      //  logTokenExpirationDetails(token); // Log expiration details for debugging
        return expirationDate.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String userName) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }





}


