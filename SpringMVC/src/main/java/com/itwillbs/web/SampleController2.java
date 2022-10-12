package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/itwill/*") //클래스에도 쓸 수 있음(매핑)
//itwill로 시작하는 주소는 모두 sampleController2와 연결하겠다 
//ex: 쇼핑몰 사이트면 -> 주문 컨트롤러 따로 분리하는 등에 활용 가능 => 각각의 컨트롤러 구분 가능 
public class SampleController2 {
	//servlet-context.xml의 beans graph참고해서 있으면 -> 스프링의 컨트롤러로 등록 완료된 것 

	

	private static final Logger log 
			= LoggerFactory.getLogger(SampleController2.class);
	//sysout은 메모리 먹으니까, log형태로 콘솔에 찍는 것임 
	
	//메서드의 리턴타입이 String일 때, 리턴되는 문자열.jsp 페이지를 호출하여 자동연결 
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg) {
		//@ModelAttribute("파라미터이름")
		//페이지 요청 시 전달되는 파라미터 이름의 정보를 사용해서 문자열 변수에 저장함 
		//+방금 저장한 정보를 뷰페이지로 전달(자동으로 처리)
		//request.setAttribute("msg",msg);(생략)
		//=> el표현식 사용해서 호출할 수 있음
		
		log.info("doC() 실행");
		log.info("msg: "+msg);
		
		
		return "itwill";
	}
	
	//==========================================================
	
	//int형태 사용불가 => 컨트롤러에서 사용가능한 형태 1.void타입  / 2.String타입 
	//1.주소이름과 같은 뷰페이지로 보내고 싶다 -> void
	//2.주소이름과 다른(리턴값과 같은) 뷰페이지로 보내고 싶다 -> String
	
	//@RequestMapping("/doC1")
	//public double doC1() {
	//	log.info("doC1() 실행");
		
	//	return 1;
//	}
	
	// /doC1 주소를 호출해서 doA.jsp페이지에 정보 출력 
	// -> 호출 주소형태: doC1?name=itwill$tel=0518030909
	@RequestMapping("/doC1")
	public String doC1(@ModelAttribute("name")String name,
			@ModelAttribute("tel") String tel){
		//데이터 두 개 @ModelAttribute 어노테이션
		//정보가 여러개? 매개변수 최대 갯수는 6개정도 너무 많으면 다른 방법 사용해야함 -> sampleController3 
		log.info("doC1(); 실행");
		log.info("msg: "+name+","+tel);
		
		return "doA";
	}
}
