package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
	
	private static final Logger log 
			= LoggerFactory.getLogger(SampleController4.class);
	
	//페이지 이동 -> /doE -(주소이동)-> /doF -(페이지이동)-> doF.jsp 
	
	@RequestMapping("/doE") //주소매핑
	public String doE(Model model, RedirectAttributes rttr) { 
		//model객체 생성, RedirectAttributes객체 생성 
		log.info("/doE() 호출");
//=====================================================================
		
		//1. 데이터 전달 (주소에 ?)
		//return "redirect:/doF?msg=itwill"; 
		//-> ?로 파라미터값 전달 -> doF.jsp에서 EL표현식을 값 받기 가능 

//----------------------------------------------------------------------
		
		//2. 데이터 전달 (model).addAttribute
		model.addAttribute("msg","busan");
		//-> Model객체 생성해서 데이터 전달 
		//주소줄에 파라미터 형태로 데이터값이 보임 
		//새로고침해도 전달한 데이터 남아 있음 
		
//----------------------------------------------------------------------
		
		//3. 데이터 전달(RedirectAttributes).addFlashAttribute
		rttr.addFlashAttribute("msg","spring");
		//->RedirectAttributes는 addFlashAttribute메서드를 가짐 
		//주소줄에 파라미터 값 보이지 않음 
		//새로고침하면 전달한 데이터 사라짐 
		
//----------------------------------------------------------------------
		//*model.addAttribute()
		//:전달 값이 URI에 표시 O, F5실행 시, 데이터 유지 (영구적)
		
		//*rttr.addFlashAttribute()
		//:전달 값이 URI에 표시 X, F5실행 시, 데이터 유지X(일시적)
//=====================================================================
		return "redirect:/doF";
		// /doE에서 /doF로 주소이동하고 싶으면 redirect: 앞에 기입하고 뒤에 이동하고자하는 주소 기입 
		//기존 JSP에서 sendredirect와 같음(주소와 화면이 둘 다 변경되며 이동하는 방식)
		
		//return "forward:/doF";
		//foward 방식도 가능함 -> 주소는 안바뀌지만 뷰(화면)이 바뀌며 이동하는 방식
	}
	
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String msg) {
		log.info("/doF() 호출");
		log.info("msg: " +msg);
	}
}
