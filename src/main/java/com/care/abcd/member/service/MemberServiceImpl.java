package com.care.abcd.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.abcd.member.dao.MemberDAO;
import com.care.abcd.member.dto.MemberDTO;

@Service
public class MemberServiceImpl {
	@Autowired MemberDAO mapper;
	
	public void insertMember(MemberDTO dto) {
		mapper.insertMember(dto);
	}
	
	public void memberView(Model model) {
		model.addAttribute("list", mapper.memberView());
	}

}
