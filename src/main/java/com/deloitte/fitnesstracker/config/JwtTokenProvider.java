package com.deloitte.fitnesstracker.config;

import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.AUTHORIZATION;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.BEARER;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.INVALID_TOKEN;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SECRET;
import static com.deloitte.fitnesstracker.constants.FitnessTrackerConstants.SECRET_VALUE;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenProvider {
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Value(SECRET_VALUE)
    private String secretKey = SECRET;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        logger.debug("Token {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,INVALID_TOKEN);
        }
    }
}
