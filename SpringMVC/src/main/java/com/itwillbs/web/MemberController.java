package com.itwillbs.web;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*") //member로 시작되는 주소 관리하는 컨트롤러 
public class MemberController {
	
	private static final Logger log 
	= LoggerFactory.getLogger(MemberController.class);
	
	//서비스 객체 주입(DI)
	@Inject
	private MemberService service;
	
	//http://localhost:8088/web/member/test(X) -> 서버 module주소 바꿈 "/"로 
	//http://localhost:8088/member/test(O)
	/*
	 * @RequestMapping("/test") 
	 * public void TestMember() {
	 * log.info("MemberController 실행!!!"); }
	 */
//=======================================================================
	
	//회원가입 GET(조회,입력)  
	@RequestMapping(value ="/insert",method = RequestMethod.GET)
					// - /member/insert로 뷰페이지 호출
	public void insertGET() {
		//메서드 타입 void/String 둘 다 사용 가능 
		//void: 주소 이름과 같은 뷰페이지명
		//String: 주소 이름과 다른 뷰페이지명 
		log.info("insertGET() 호출");
		log.info("연결된 View 페이지 출력 ");
	}
	
//------------------------------------------------------------------------
	
	//회원가입 POST(처리)
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	//기존 jsp에서는 inserPro라고 따로 가상 주소를 사용해지만, 
	//GET이든 POST든 가상주소는 "/insert"로 통일하고, 방식에 따라 메서드 달라짐 
	public String insertPOST(MemberVO vo,HttpServletRequest request) throws UnsupportedEncodingException {
		//페이지 이동(redirect)하기 위해서 POST방식의 메서드 타입은 String이어야 함 
		
		log.info("insertPOST(); 호출");
		//--------------------------------------------------------
		/*
		 * //<기존 JSP방식> 
		 * //한글처리 -> throws exception 예외처리
		 * request.setCharacterEncoding("UTF-8");
		 * 
		 * //전달정보 저장(userid,userpw,username,useremail) MemberVO vo = new MemberVO();
		 * vo.setUserid(request.getParameter("userid"));
		 * vo.setUserid(request.getParameter("userpw"));
		 * vo.setUserid(request.getParameter("username"));
		 * vo.setUserid(request.getParameter("useremail"));
		 * 
		 * log.info(vo+"");
		 */
		//--------------------------------------------------------
		//<Spring 방식>:메서드 매개변수에 MemberVo vo추가
		//-> 파라미터 값 자동으로 찾아줌 
		
		//한글처리 -> web.xml
		log.info(vo+"");
		
		//회원가입 -> 서비스 -> DB에 저장 (DAO객체 생성)
		
		//메서드 밖 MemberService 객체 주입 (DI) 
		service.memberJoin(vo);
		log.info("회원가입 성공");
		
		//페이지 이동(로그인 페이지로)
		
		
		//return "/member/login";
		return "redirect:/member/login"; //주소+페이지 둘 다 변경하며 이동 
	}
//=======================================================================
	//가상 주소는 동일하게 사용 ,method방식으로 나눔 
	
	//로그인 GET : 초기에 로그인 화면으로 이동하면 화면 보여줌 
	@RequestMapping(value = "/login",method =RequestMethod.GET)
	public String loginGET() {
		log.info("loginGET() 실행");
		log.info("연결된 뷰페이지로 이동");
		
		//return "memberLogin";(X)
		return "/member/memberLogin";
	}
//-------------------------------------------------------------------------	
	//로그인 POST : 로그인 화면에서 로그인정보 입력하고 로그인버튼 누르면 '로그인 동작 실행'
	@RequestMapping(value = "/login", method =RequestMethod.POST)
	public String loginPOST(/*
							 * @ModelAttribute("userid") String userid ,
							 */MemberVO vo,HttpSession session) {
							 //MemberVO로 변수들 값 받아오고, HttpSession으로 세션에 로그인정보 저장 
		log.info("loginPOST() 실행");
		
		//한글처리 => web.xml 필터사용(생략)
	
		//전달 정보 저장(파라미터 -userid,userpw)
		log.info("vo: "+vo);
		//DB에서 확인(컨트롤러 -> 서비스 -> DAO)
		MemberVO loginVO = service.memberLogin(vo);
		log.info("loginVO: "+loginVO);
		//로그인 여부 확인
		if(loginVO != null) {
			//성공 -> 메인페이지 이동, 로그인정보를 저장(세션)
			
			//JSP(View)에서 session정보를 가져와서 사용
			session.setAttribute("loginVO", loginVO); //로그인 정보를 세션에 담아서 보냄 
			return "redirect:/member/main";
			//redirecr: 주소 바꿔서 이동 
		}else {
			//실패 -> 로그인 페이지 이동 
			return "redirect:/member/login";			
		}
		
	}
//=======================================================================	
	//메인페이지 GET
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public void mainGET() {
		log.info("mainGET() 호출");
		log.info("void리턴: /member/main.jsp 뷰 호출");
	}
//=======================================================================	
	//로그아웃 GET
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		//로그아웃 -> 세션초기화 
		session.invalidate();
		log.info("세션 초기화 완료 -> 로그아웃");
		
		//페이지 이동
		return "redirect:/member/main";
	}
//=======================================================================	
	//회원정보 조회 GET
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public void infoGET(HttpSession session,Model model) {
		log.info("infoGET() 호출");
		
		//main페이지 -> ID정보 -> info페이지
		MemberVO vo =(MemberVO)session.getAttribute("loginVO");
		//vo.getUserid();
		log.info("ID: "+vo.getUserid());
		//서비스 사용 -> DB정보를 가져오기
		MemberVO userVO = service.memberGet(vo.getUserid());
		log.info("userVO: "+userVO);
		// 전달정보를 Model객체에 저장 -> View 출력
		model.addAttribute("userVO",userVO);
		
	}
}
