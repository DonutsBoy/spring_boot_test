package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.HelloService;

@RestController
public class HelloController {
	
	//3333
	@Autowired
	HelloService helloService;
		
	@GetMapping("/hello")
	public String hello() {
		return "안녕11111111111111111111 hello 노재웅ㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ 하이";
	}
	
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK) //이건 써줘도 되고 안써줘도 정상실행 된다. ??
    @CrossOrigin("*")
    public Map<String, Object> hello2(HttpServletRequest request) {
    	//System.out.println("name : " + request.getParameter("name"));
    	
    	HashMap<String, Object> hashmap = new HashMap<String, Object>();
    	hashmap.put("name", request.getParameter("name"));
    	hashmap.put("age", request.getParameter("age"));
    	hashmap.put("etc", helloService.getUser("20"));
    	
        return hashmap;
    }

    @RequestMapping(value = "/helloDelete", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK) //이건 써줘도 되고 안써줘도 정상실행 된다. ??
    @CrossOrigin("*")
    public void helloDelete(HttpServletRequest request) {
    	helloService.helloDelete();
    }


    @RequestMapping(value = "/helloInsert", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK) //이건 써줘도 되고 안써줘도 정상실행 된다. ??
    @CrossOrigin("*")
    public void helloInsert(HttpServletRequest request) {
    	helloService.helloInsert();
    }
    
}
