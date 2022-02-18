package com.care.abcd.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.care.abcd.member.dto.MemberDTO;


public interface MemberDAO {
	public void insertMember(MemberDTO dto);
	public ArrayList<MemberDTO> memberView();

}
