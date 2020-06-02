package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfileModel;

@RestController
public class UserProfileController {
		
		//@Autowired
		UserProfileMapper userProfileMapper;
		
		private Map<String, UserProfileModel> userMap;
		
		public UserProfileController(UserProfileMapper userProfileMapper) {
			this.userProfileMapper = userProfileMapper;
		}
		
		//@PostConstruct 어노테이션은 스프링프레임워크가 UserProfileController 라는 클래스 인스턴스 만들고나서 그후에 호출하게 된다. 
		@PostConstruct
		public void init() {
			userMap = new HashMap<String, UserProfileModel>();
			userMap.put("1", new UserProfileModel("1","이준식","01022223333","서울시 강남구 대치동"));
			userMap.put("2", new UserProfileModel("2","서효리","01011112222","서울시 강남구 옥수동"));
		}
		
		@GetMapping("/usr/{id}")
		public UserProfileModel getUserProfile(@PathVariable("id") String id) {
			return userProfileMapper.getUserProfile(id);
			//return userMap.get(id);
		}
		
		@GetMapping("/usr/all")
		public List<UserProfileModel> getUserProfileList() {
			//HashMap의 2개 value를 ArrayList로 변환하여 리턴한다
			return new ArrayList<UserProfileModel>(userMap.values());
		}
		
		@PutMapping("/usr/{id}")
		public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address ) {
			userProfileMapper.putUserProfile(id, name, phone, address);
//			UserProfileModel userProfileModel = new UserProfileModel(id, name, phone, address);
//			userMap.put(id, userProfileModel);
		}
		
		@PostMapping("/usr/{id}")
		public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address ) {
			UserProfileModel userProfileModel = userMap.get(id);
//			userProfileModel.setName(name);
//			userProfileModel.setPhone(phone);
//			userProfileModel.setAddress(address);	
			//userMap.put(id, userProfileModel);
		}
		
		@DeleteMapping("/usr/{id}")
		public void deleteUserProfile(@PathVariable("id") String id) {
			userMap.remove(id);
		}
}