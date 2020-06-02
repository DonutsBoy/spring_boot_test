package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HelloDAO;
import com.example.demo.mapper.HelloMapper;

@Service
@Transactional
public class HelloServiceImpl implements HelloService {

	@Autowired
	HelloMapper helloMapper;
	
	@Autowired
	HelloDAO helloDAO;
	
	public List<Map<String, Object>> getUser(String id) {
		
		//DAO 클래스를 이용한 스프링 프로젝트 구조
		//return helloMapper.selectList2(id);
		
		//Mapper 인터페이스를 이용한 스프링 프로젝트 구조
		return helloDAO.selectList2(id);
	}
	
	public void helloDelete() {
		helloDAO.helloDelete();		
	}
	public void helloInsert() {
		helloDAO.helloInsert();		
	}
	
}
