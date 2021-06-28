package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.HelloDAO;
import com.example.demo.mapper.HelloMapper;

@Service
//아래와 같은 역할을 한다. DEFAULT 는 기본값이다.
@Transactional
//sqlite3 에서 아래로 하면 잘됨
//@Transactional(isolation=Isolation.DEFAULT)

//sqlite3에서 아래 설정으로 하면 서버에러 500 (Internal Server Error) 리턴함
//@Transactional(isolation=Isolation.READ_COMMITTED)

//READ_UNCOMMITTED 설명
//어떤 사용자가 A라는 데이터를 B라는 데이터로 변경하는 동안 다른 사용자는 B라는 
//아직 완료되지 않은(Uncommitted 혹은 Dirty) 데이터 B를 읽을 수 있다.
//sqlite3에서 아래 설정으로 하면 서버처리 잘됨
//@Transactional(isolation=Isolation.READ_UNCOMMITTED)

//sqlite3 에서 아래로 하면 서버에서 500 (Internal Server Error) 리턴함
//@Transactional(isolation=Isolation.REPEATABLE_READ)
//sqlite3 에서 아래로 하면 잘됨
//@Transactional(isolation=Isolation.SERIALIZABLE)
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
	
	//일별수익 조회
	public List<Map<String, Object>> dayProfitSelect(Map<String, Object> paramMap) {
		return helloDAO.dayProfitSelect(paramMap);
	}
	
	public List<Map<String, Object>> todoList() {
		return helloDAO.todoList();
	}
	
	public void todoInsert(Map<String, Object> paramMap) {
		helloDAO.todoInsert(paramMap);
	}
	
	public void todoAllDelete() {
		helloDAO.todoAllDelete();
	}
	
	public void todoDelete(Map<String, Object> paramMap) {
		helloDAO.todoDelete(paramMap);		
	}
	
	public void todoCheckBoxUpdate(Map<String, Object> paramMap) {
		helloDAO.todoCheckBoxUpdate(paramMap);		
	}
	
}
