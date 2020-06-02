package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

//아래 @Mapper 어노테이션 필수로 붙어야함
@Mapper
public interface HelloMapper {

	public String selectList2(String param);

}
