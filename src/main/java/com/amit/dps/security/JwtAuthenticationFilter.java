package com.amit.dps.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amit.dps.exceptions.ResourceNotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException,ExpiredJwtException,MalformedJwtException,ResourceNotFoundException {
//		1.get Token
		String requestToken=request.getHeader("Authorization");
//		Bearer 2352523sdgsd
//		System.out.println(requestToken); //for our own perpose
		
		
		
		String username=null;
		
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token=requestToken.substring(7);
			try {
				username=this.jwtTokenHelper.getUsernameFromToken(token);
				System.out.println("username : "+username);
			}catch(IllegalArgumentException ex) {
				System.out.println("Unable to get jwt token"  + ex.getMessage());
				//send to client here
			}catch(ExpiredJwtException ex) {
				
				System.out.println("Jwt token has expired : " + ex.getMessage());
				
			}
			catch(MalformedJwtException ex) {
				System.out.println("invalid jwt " + ex.getMessage());

				
			}
			
		}
		else {
			System.out.println("jwt token does not start with Bearer : "+ requestToken);
		}
		//once we get the token 
		//now validate
		if(username!=null && SecurityContextHolder .getContext().getAuthentication()==null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				//shi chal raha hai
				//authenticate karna hai
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
			}
			else {
				System.out.println("invalid jwt token validation failed ");
			}
			
		}
//		else {
//			System.out.println("username is null or context is not null");
//			
//		}
		
		filterChain.doFilter(request, response);
		
	}

}
