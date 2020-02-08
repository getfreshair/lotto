package com.sadang.lotto.service;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sadang.lotto.exception.ResourceNotFoundException;
import com.sadang.lotto.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{
	private Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	private static final String SALT =  "luvookSecret";

	@Override
	public <T> String create(String key, T data, String subject) 
			throws ResourceNotFoundException {
		String jwt = Jwts.builder()
						.setHeaderParam("typ", "JWT")
						.setHeaderParam("regDate", System.currentTimeMillis())
						.setSubject(subject)
						.signWith(SignatureAlgorithm.HS256, this.generateKey())
						.compact();

		return jwt;
	}
	
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if(logger.isErrorEnabled()) {
				logger.error(e.getMessage());
			}else {
				logger.error("Making JWT Key Error ::: {}" + e.getMessage());
			}
		}
		
		return key;
	}

	@Override
	public boolean isUsable(String jwt) {
		try {
			@SuppressWarnings("unused")
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(this.generateKey())
					.parseClaimsJws(jwt);

			return true;
		} catch(ExpiredJwtException e) {
			// JWT를 생성할 때 지정한 유효기간 초과할 때
			throw new UnauthorizedException(e.getMessage());
		} catch(UnsupportedJwtException e) {
			// 예상하는 형식과 일치하지 않는 특정 형식이나 구성의 JWT일 때
			throw new UnauthorizedException(e.getMessage());
		} catch(MalformedJwtException e) {
			// JWT가 올바르게 구성되지 않았을 때
			throw new UnauthorizedException(e.getMessage());
		} catch(SignatureException e) {
			// JWT의 기존 서명을 확인하지 못했을 때
			throw new UnauthorizedException(e.getMessage());
		} catch(IllegalArgumentException e) {
			throw new UnauthorizedException(e.getMessage());
		} catch(Exception e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}
}
