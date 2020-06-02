package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HelloService {
	
	public List<Map<String, Object>> getUser(String id);
	public void helloDelete();
	public void helloInsert();
		
}
