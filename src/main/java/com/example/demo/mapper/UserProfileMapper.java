package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.UserProfileModel;

@Mapper
public interface UserProfileMapper {

	@Select("SELECT * FROM USERPROFILE WHERE ID = #{id}")
	UserProfileModel getUserProfile(@Param("id") String id);
	
	@Insert("INSERT INTO USERPROFILE (ID, NAME, PHONE, ADDRESS) VALUES (#{id}, #{name}, #{phone}, #{address})")
	int putUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
	
}
