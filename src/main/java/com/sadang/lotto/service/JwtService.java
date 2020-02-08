package com.sadang.lotto.service;

import com.sadang.lotto.exception.ResourceNotFoundException;
import com.sadang.lotto.exception.UnauthorizedException;

public interface JwtService {
	public <T> String create(String key, T data, String subject) throws ResourceNotFoundException;
	public boolean isUsable(String jwt) throws UnauthorizedException;
}
