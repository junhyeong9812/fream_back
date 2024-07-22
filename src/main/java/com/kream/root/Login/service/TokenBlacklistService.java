package com.kream.root.Login.service;

import java.util.Date;

public interface TokenBlacklistService {
	void blacklistToken(String token, Date expiration);
	boolean isTokenBlacklisted(String token);
	
}
