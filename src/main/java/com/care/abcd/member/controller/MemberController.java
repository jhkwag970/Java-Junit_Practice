package com.care.abcd.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.care.abcd.member.dto.MemberDTO;
import com.care.abcd.member.service.MemberServiceImpl;

@Controller
public class MemberController {
	@Autowired MemberServiceImpl ms;
	
	@GetMapping("index")
	public String index() {
		return "member/index";
	}
	
	@GetMapping("insertview")
	public String insertview() {
		
		return "member/insertview";
	}
	
	@PostMapping("insert")
	public String insert(MemberDTO dto, Model model) {
		ms.insertMember(dto);
		return "redirect:index";
	}
	
	@GetMapping("memberview")
	public String memberview(Model model) {
		ms.memberView(model);
		return "member/memberview";
	}
}
