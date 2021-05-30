package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginTokenService {
	
	@Autowired
	JwtService jwtService;
	
//    private final JwtService jwtService;
//    // 의존성 주입
//    public LoginTokenService(JwtService jwtService) {
//    	logger.debug("11111");
//        this.jwtService = jwtService;
//    }
    
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> loginToken(Map<String, Object> paramMap) {
		if ("admin".equals(paramMap.get("id"))) {
			//사용자인덱스번호
			int userIdx = 100;			
			final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(userIdx));
			paramMap.put("success", true);
			paramMap.put("token", tokenDto.getToken());
			paramMap.put("tokenDecode", jwtService.decode(tokenDto.getToken()));
		} else {
			paramMap.put("success", false);			
			paramMap.put("token", "");
		}
		return paramMap;
	}

	public Map<String, Object> loginTokenCheck(Map<String, Object> paramMap) {
		if (!"".equals(paramMap.get("token"))) {
			int tokenDecode = jwtService.decode((String) paramMap.get("token"));
			logger.debug("tokenDecode : {}", tokenDecode);
			if (tokenDecode != -1) {
				paramMap.put("success", true);										
			} else {
				paramMap.put("success", false);														
			}
		} else {
			paramMap.put("success", false);						
		}
		return paramMap;
	}
}