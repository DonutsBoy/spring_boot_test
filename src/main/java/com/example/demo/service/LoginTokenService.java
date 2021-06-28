package com.example.demo.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginTokenService {
	
	@Autowired
	JwtService jwtService;
	
    @Value("${UPBIT.ACCESSKEY}")
    private String ACCESSKEY;

    @Value("${UPBIT.SECRETKEY}")
    private String SECRETKEY;
	
//    private final JwtService jwtService;
//    // 의존성 주입
//    public LoginTokenService(JwtService jwtService) {
//    	logger.debug("11111");
//        this.jwtService = jwtService;
//    }
    
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> loginToken(Map<String, Object> paramMap) {
		if ("admin".equals(paramMap.get("id")) && "qwer1234".equals(paramMap.get("pw"))) {
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

	public List<Map<String, Object>> upbitAccountSelect(Map<String, Object> paramMap) {
		
//        String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
//        String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
//        String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");

		logger.debug("ACCESSKEY {}", ACCESSKEY);
		logger.debug("SECRETKEY {}", SECRETKEY);
		
		String accessKey = ACCESSKEY;
		String secretKey = SECRETKEY;
		String serverUrl = "https://api.upbit.com";
		String rtnStr = "";
		
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;
        logger.debug("authenticationToken {}", authenticationToken);
        
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            rtnStr = EntityUtils.toString(entity, "UTF-8");
            
            //System.out.println(EntityUtils.toString(entity, "UTF-8"));
            logger.debug("rtnStr : {}", rtnStr);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		List<Map<String, Object>> hashMap = null;
		try {
			
			//JSON String -> Map<String, Object> 으로 변환
			//hashMap = new ObjectMapper().readValue(response, HashMap.class);
			
			//JSON String -> List<Map<String, Object>> 으로 변환
			hashMap = new ObjectMapper().readValue(rtnStr, new TypeReference<List<Map<String, Object>>>(){});
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	        
        
		return hashMap;
	}
		
}