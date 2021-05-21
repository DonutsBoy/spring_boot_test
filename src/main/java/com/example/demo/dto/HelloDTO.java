package com.example.demo.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Data
public class HelloDTO {
	
	private String memberId = ""; //1111
	
	private String memberName = "";

	
}
