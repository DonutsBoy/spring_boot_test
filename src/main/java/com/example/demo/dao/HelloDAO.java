package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.HelloDTO;

//아래 @Repository 어노테이션 필수로 붙어야함
@Repository
public class HelloDAO {

	@Autowired
	SqlSession sqlSession;
	
	public List<Map<String, Object>> selectList2(String id) {
		return sqlSession.selectList("com.example.demo.mapper.HelloMapper.selectList2", id);
	}
	
	public void helloDelete() {
		sqlSession.delete("com.example.demo.mapper.HelloMapper.helloDelete", null);
	}
	
	public void helloInsert() {
		Map<String, Object> paramMap = new HashMap<String, Object>(); //returnMap
		paramMap.put("A", "1111");
		paramMap.put("B", "222222");
		sqlSession.delete("com.example.demo.mapper.HelloMapper.helloInsert", paramMap);
	}
	
}
