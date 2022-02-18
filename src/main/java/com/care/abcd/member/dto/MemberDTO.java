package com.care.abcd.member.dto;

import java.lang.reflect.Member;

public class MemberDTO {
	private int id;
	private String name;
	
	public MemberDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public MemberDTO() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
