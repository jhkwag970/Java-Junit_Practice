package com.care.abcd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.abcd.member.controller.MemberController;
import com.care.abcd.member.dao.MemberDAO;
import com.care.abcd.member.dto.MemberDTO;
import com.care.abcd.member.service.MemberService;
import com.care.abcd.member.service.MemberServiceImpl;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"classpath:testMember.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestMember {
	@Autowired MemberController mc;
	@Autowired MemberServiceImpl ms;
	@Autowired MemberDAO dao;
	
	@Test
	public void testMc() {
		System.out.println("---실행===mc \n" + mc);
		assertNotNull(mc);
	}
	
	@Test
	public void testMs() {
		System.out.println("---실행===ms \n"+ms);
		assertNotNull(ms);
		
		MemberDTO dto = new MemberDTO(333,"test3");
		ms.insertMember(dto);
		
	}
	
	@Test
	public void testDao() {
		System.out.println("---실행===dao \n"+dao);
		assertNotNull(dao);
		
		MemberDTO dto = new MemberDTO(111,"test");
		dao.insertMember(dto);		
	}
	
}
