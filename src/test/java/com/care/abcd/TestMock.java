package com.care.abcd;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.abcd.member.controller.MemberController;
import com.care.abcd.member.dao.MemberDAO;
import com.care.abcd.member.dto.MemberDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= {
		"classpath:testMember.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestMock {
	
	@Autowired MemberController mc;
	MockMvc mock;
	

	//test실행전 먼저 실행됨
	@Before
	public void setUp() {
		System.out.println("test실행 전 ------");
		//총합 테스트: mock이 컨트롤러 기능을 가져옴
		mock = MockMvcBuilders.standaloneSetup(mc).build();
		
	}
	
	@Test
	public void testIndex() throws Exception {
		System.out.println("----test 코드 실행---- ");
		//perform > get 방식으로 url 요청 받아오기
		//.andDo 현재 상태 (통신 상태 404 or 500 200 etc..)
		//andExpect > status.ok > 200 일시 통신 성공
		//andExpect >forwardedUrl > 성공시 이동 주소가 index return 주소가 맞는지 확인
		mock.perform(get("/index"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("member/index"));
	}
	
	@Autowired
	MemberDAO dao;
	
	@Test
	public void daoIns() {
		ArrayList<MemberDTO> arr = dao.memberView();
		assertNotNull(arr);
		for(MemberDTO test : arr) {
			System.out.println("id"  + test.getId());
			System.out.println("name: " + test.getName());
			System.out.println("-------------");
		}
	}
	
	@Test
	//값이 db들어가는지 확인 하지만, 실제 데이터를 넣지는 않음 (rollback 기능이라고 보면 됨)
	@Transactional(transactionManager = "txMgr")
	public void testInsert() throws Exception{
		//status = 302 (redirect) 성공
		// is3xxredirection (300에 관련된 status인지 확인 해줌)
		mock.perform(post("/insert").param("id", "999").param("name", "길길이"))
		.andDo(print())
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testMemberview() throws Exception{
		//model의 값에 list라고 맵핑된 값이 있는지
		mock.perform(get("/memberview"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("member/memberview"))
		.andExpect(model().attributeExists("list"));
	}
}
