package com.amit.dps.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.amit.dps.config.AppConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	
	private String secret="jwtTokenKey";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//Retrieve expiration date from token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	//
	public <T> T getClaimFromToken(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
//	for retrieving any information from token we need the security key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		
		String str=doGenerateToken(claims,userDetails.getUsername());
		return str;
	}	
	//while creating the token
//		1.define claims of the token, Like issuer,Expiration, Subject and the Id
//		2.Sign the jwt using HS512 algorithm and security key
//		3.According to JWT serialization(https://tools.ietf.org/html/draft-ietf-jose-j
//		compaction of the  JWT to a URL safe String
		
	private String doGenerateToken(Map<String,Object> claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
		.setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_TOKEN_VALIDITY *100 ))
		.signWith(SignatureAlgorithm.HS512, secret).compact();
				    			    
	}
	//validate token
	public  Boolean validateToken(String token,UserDetails userDetails) {
		final String username=getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

}
