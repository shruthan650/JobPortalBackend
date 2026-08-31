package com.shruthan.jobportalbackend.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

	@Value("${jwt.secret}")
	private String secret;
	private SecretKey sk;

	@PostConstruct
	public void init() {
		
		byte[] encoded = Decoders.BASE64.decode(secret);
		sk = Keys.hmacShaKeyFor(encoded);
		
	}

	public String generateToken(String username) {
		return Jwts.builder().subject(username).expiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
				.signWith(sk).compact();
	}
}
