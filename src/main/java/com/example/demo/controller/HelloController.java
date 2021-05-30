package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.service.HelloService;
import com.example.demo.service.LoginTokenService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HelloService helloService;

	@Autowired
	LoginTokenService loginTokenService;

//    private final LoginTokenService loginTokenService;
//    // 생성자 의존성 주입
//    public HelloController(LoginTokenService loginTokenService) {
//        this.loginTokenService = loginTokenService;
//    }	
	
	@GetMapping("/api/hello")
	public String hello() {
		return "0000";
	}
	
    @RequestMapping(value = "/api/hello2", method = RequestMethod.GET)
    public Map<String, Object> hello2(HttpServletRequest request) {
    	System.out.println("name : " + request.getParameter("name"));

    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	hashmap.put("name", request.getParameter("name"));
    	hashmap.put("age", request.getParameter("age"));
    	hashmap.put("e테이ㅡㄹtc", helloService.getUser("20"));
    	
        return hashmap;
    }

    @RequestMapping(value = "/api/login/session", method = RequestMethod.GET)
    public Map<String, Object> login(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws InterruptedException {
    	Thread.sleep(500); //0.5초 대기        
    	if ("admin".equals((String) paramMap.get("id"))) {
        	HttpSession session = request.getSession(true);//HttpSession이 존재하면 현재 HttpSession을 반환하고 존재하지 않으면 새로 세션을 생성한다.(기본값:true)
            session.setAttribute("id", paramMap.get("id"));
            paramMap.put("success", true);
            logger.debug("session.getId() {}", session.getId());//세션 고유의 ID를 문자열 형태로 리턴
    	} else {
            paramMap.put("success", false);    		
    	}
        return paramMap;
    }

    @RequestMapping(value = "/api/login/session/check", method = RequestMethod.GET)
    public Map<String, Object> loginSessionCheck(@RequestParam Map<String, Object> paramMap, HttpSession session) {
    	if(session.getAttribute("id") == null) {
            paramMap.put("success", false);
    	} else {
            paramMap.put("success", true);
    	}
        return paramMap;
    }

    @RequestMapping(value = "/api/logout/session", method = RequestMethod.GET)
    public Map<String, Object> logoutSession(@RequestParam Map<String, Object> paramMap, HttpSession session) {
    	session.removeAttribute("id");
    	session.invalidate();//세션값 전체삭제
        return paramMap;
    }
    
    @RequestMapping(value = "/api/login/token", method = RequestMethod.GET)
    public Map<String, Object> loginToken(@RequestParam Map<String, Object> paramMap, HttpServletRequest request) throws InterruptedException {
    	Thread.sleep(500); //0.5초 대기        
        return loginTokenService.loginToken(paramMap);
    }
    
    @RequestMapping(value = "/api/login/token/check", method = RequestMethod.GET)
    public Map<String, Object> loginTokenCheck(@RequestParam Map<String, Object> paramMap, HttpSession session) {
        return loginTokenService.loginTokenCheck(paramMap);    	
    }

/*
기본적으로 JWT는 서버쪽에서 로그아웃을 할 수 없습니다. 그래서 보통은 JWT 토큰 주기를 짧게 하거나 또는 로그아웃 기능을 구현하려고 토큰을 DB에 보존하고 매 요청시 마다 로그아웃으로 인해 DB에서 삭제된 토큰이 아닌지등을 검사하여 비슷하게 구현할 수 있습니다. 이외에도 비슷하지만 다른 구현방법이 있습니다.
외부에 API를 제공하여 사용하는게 아니면 JWT 사용하지 않고 단순 Cookie Session 방식을 사용하시기를 권해드립니다.
그럼 jwt에서 로그아웃까지 구현하려면 세션 처리하던 방식처럼 DB에 저장했다가 로그아웃 요청 들어오면 DB에서 체크해서 처리하는 방법으로 할 수 있다는 말씀이시죠??
네 맞습니다. JWT이 토큰이 유효한지, 유효한 상태에서 로그아웃을 시도했는지 등을 모두 처리해야해서 복잡도가 꽤 증가합니다. 특별하게 JWT를 사용해야할 이유가 없으면 간단한 방식을 사용하는것을 권하는 이유입니다.     
 */
//    @RequestMapping(value = "/api/logout/token", method = RequestMethod.GET)
//    public Map<String, Object> logoutToken(@RequestParam Map<String, Object> paramMap, HttpSession session) {
//    	//11
//        return paramMap;
//    }
    
    @RequestMapping(value = "/api/helloDelete", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK) //이건 써줘도 되고 안써줘도 정상실행 된다.
    @CrossOrigin("*")
    public void helloDelete(HttpServletRequest request) {
    	helloService.helloDelete();
    }

    @RequestMapping(value = "/api/helloInsert", method = RequestMethod.GET)
    public void helloInsert(HttpServletRequest request) {
    	System.out.println("11111111111111");
    	helloService.helloInsert();
    }
    
    //일별수익 조회
    @RequestMapping(value = "/api/auto/dayProfit/select", method = RequestMethod.GET)
    public List<Map<String, Object>> dayProfitSelect(@RequestParam Map<String, Object> paramMap) throws InterruptedException {
    	logger.debug("zzzz : {}", paramMap.get("profitLossGubun"));
    	Thread.sleep(500); //0.5초 대기
    	return helloService.dayProfitSelect(paramMap);
    }

    @RequestMapping(value = "/api/todo/list", method = RequestMethod.GET)
    public List<Map<String, Object>> todoList() throws InterruptedException {
    	Thread.sleep(500); //0.5초 대기
    	return helloService.todoList();
    }
    
    @RequestMapping(value = "/api/todo/insert", method = RequestMethod.GET)
    public void todoInsert(@RequestParam Map<String, Object> paramMap) throws Throwable {
    	System.out.println("z : " + paramMap.get("TITLE"));
    	helloService.todoInsert(paramMap);
    }
    
    @RequestMapping(value = "/api/todo/allDelete", method = RequestMethod.GET)
    public void todoAllDelete() throws Throwable {
    	helloService.todoAllDelete();
    }

    @RequestMapping(value = "/api/todo/delete", method = RequestMethod.GET)
    public void todoDelete(@RequestParam Map<String, Object> paramMap) throws Throwable {
    	helloService.todoDelete(paramMap);
    }
    
    @RequestMapping(value = "/api/todo/checkBoxUpdate", method = RequestMethod.GET)
    public void todoCheckBoxUpdate(@RequestParam Map<String, Object> paramMap) throws Throwable {
    	helloService.todoCheckBoxUpdate(paramMap);
    }
 
    @RequestMapping(value = "/api/upbit/coin/info", method = RequestMethod.GET)
    public List<Map<String, Object>> upbitCointInfo() throws InterruptedException {
		//GET 방식 REST API 호출
		RestTemplate restTemplate = new RestTemplate(); 
		URI uri = UriComponentsBuilder
				.fromHttpUrl("https://api.upbit.com/v1/ticker?markets=KRW-BTC")
				.queryParam("name", "홍길동")
				.queryParam("age", 30)
				.build()
				.encode() //이 옵션을 안 써주면 GET방식 파라미터에 한글을 넣으면 "HttpClientErrorException: 400 null" 오류가 발생함
				.toUri();
		String response = restTemplate.getForObject(uri, String.class);
		logger.debug("response : {}", response);

		List<Map<String, Object>> hashMap = null;
		try {
			
			//JSON String -> Map<String, Object> 으로 변환
			//hashMap = new ObjectMapper().readValue(response, HashMap.class);
			
			//JSON String -> List<Map<String, Object>> 으로 변환
			hashMap = new ObjectMapper().readValue(response, new TypeReference<List<Map<String, Object>>>(){});
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}			
		
    	return hashMap;
    }
    
}