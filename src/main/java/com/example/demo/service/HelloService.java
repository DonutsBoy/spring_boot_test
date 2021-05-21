package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HelloService {
	
	public List<Map<String, Object>> getUser(String id);
	public void helloDelete();
	public void helloInsert();

	//일별수익 조회
	public List<Map<String, Object>> dayProfitSelect(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> todoList();
	
	public void todoInsert(Map<String, Object> paramMap);
	
	public void todoAllDelete();
	
	public void todoDelete(Map<String, Object> paramMap);
	
	public void todoCheckBoxUpdate(Map<String, Object> paramMap);
	
}
