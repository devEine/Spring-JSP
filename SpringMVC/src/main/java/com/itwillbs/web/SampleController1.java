package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller : 해당클래스를 컨트롤러로 사용하도록 스프링에 등록하는 것 
@Controller //JSP에서 HttpServlet상속받는 것과 달리 Spring은 컨트롤러 어노테이션으로 대체 가능
public class SampleController1 {
	
	

	private static final Logger log 
			= LoggerFactory.getLogger(SampleController1.class);
	
	// http://localhost:8080/web/doA
	//=============================================================
	
	//@GetMapping("/doA") //GET방식으로 들어올 때 매핑
	//@RequestMapping("/doA") //어떤 방식으로 들어오든 매핑, 디폴트값은 GET
	@RequestMapping(value = "/doA",method = RequestMethod.GET)
	//:RequestMapping으로 방식을 나타내고 싶을 때 사용 
	public void doA() {
		log.info("/doA 주소 호출, doA() 실행 ");
	}
	
	//=============================================================
	
	//어노테이션으로 매핑만하면 -> 해당하는 jsp뷰 연결해줌 
	
	//매핑되는 가상주소에 따라 연결되는 뷰가 달라진다 (메서드명은 상관없음)
	//=> 메서드의 리턴타입이  void일 때, 호출되는 주소를 사용해서 뷰를 연결 
	// /views/주소.jsp 페이지를 연결하고 자동으로 호출함 
	@RequestMapping("/doB1")
	public void doB() {
		log.info("/doB 주소 호출 -> doB.jsp페이지 호출 ");
	}
}
