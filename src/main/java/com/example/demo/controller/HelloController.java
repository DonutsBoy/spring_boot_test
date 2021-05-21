package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloService;

@RestController
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	HelloService helloService;
		
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
    	logger.debug("zzzz : {}", "1111");
    	Thread.sleep(500); //0.5초 대기
    	return helloService.dayProfitSelect(paramMap);
    }

    @RequestMapping(value = "/api/todo/list", method = RequestMethod.GET)
    public List<Map<String, Object>> todoList() throws InterruptedException {
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
    
}