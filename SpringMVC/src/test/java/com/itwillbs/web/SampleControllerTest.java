package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//스프링, MVC전용 테스트파일로 설정 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
public class SampleControllerTest {
	//컨트롤러는 아니지만, 컨트롤러의 역할을 하는 테스트 파일 
	//Server:Stop을 전제로 함 -> 서버의 원활한 운용위해

	private static final Logger log 
			= LoggerFactory.getLogger(SampleControllerTest.class);
	
	//객체주입
	//WebAppConfiguration : 웹프로젝트 객체
	@Inject
	private WebAppConfiguration waCTX;
	
	//MockMvc :테스트 전용 객체 -> 브라우저에서 요청(request),응답(response)처리객체 
	private MockMvc mocMvc;
	
	@Before //junit의 어노테이션 : @Test전에 실행시킬 메서드 
	public void setUp() {
		this.mocMvc 
		 = MockMvcBuilders.webAppContextSetup(this.waCTX).bulid();		
		log.info("테스트 전 사전 준비 완료!");
	
	}
	
	public void testDoA() {
		log.info("Junit이 실행하여 테스트");
		
		try {
		mocMvc.perform(MockMvcRequestBuilders.get("/doE"));
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
