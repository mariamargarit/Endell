//package dd.projects.ddshop.utils;
//
//import dd.projects.ddshop.dtos.UserLoginDTO;
//import dd.projects.ddshop.exceptions.JwtTokenMalformedException;
//import dd.projects.ddshop.exceptions.JwtTokenMissingException;
//import org.springframework.beans.factory.annotation.Value;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.security.SignatureException;
//import java.util.Date;
//import io.jsonwebtoken.*;
//
//@Component
//public class JwtUtils {
//    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//
//    @Value("${endell.app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${endell.app.jwtExpirationMs}")
//    private int jwtExpirationMs;
//
//    public String generateJwtToken(Authentication authentication) {
//
//        UserLoginDTO userPrincipal = (UserLoginDTO) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject((userPrincipal.getEmail()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public String getUserNameFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public void validateJwtToken(final String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//        } catch (MalformedJwtException ex) {
//            throw new JwtTokenMalformedException("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            throw new JwtTokenMalformedException("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            throw new JwtTokenMalformedException("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            throw new JwtTokenMissingException("JWT claims string is empty.");
//        }
//
//    }
//
//}
