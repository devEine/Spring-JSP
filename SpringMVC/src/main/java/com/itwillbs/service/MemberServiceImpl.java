package com.itwillbs.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service //스프링(root-context.xml)에서 해당 파일을 서비스롤 인식하기 위해 어노테이션 붙임 
public class MemberServiceImpl implements MemberService{
	
	

	private static final Logger log 
			= LoggerFactory.getLogger(MemberServiceImpl.class);
	


//MemberDAO 객체를 주입(DI) - 객체를 직접 생성하지 않고, 만들어진 것 사용(root-context.xml)
@Inject
	private MemberDAO dao;

//=========================================================================================
//회원가입 메서드 재정의 
@Override
	public void memberJoin(MemberVO vo) {
		log.info("컨트롤러 -> 서비스(impl)");
		log.info("MemberServiceImpl-memberJoin()호출");
		log.info("서비스(Impl) -> DAO(Impl)");
		//DAO객체 생성 - 메서드 호출
		dao.insertMember(vo);
		
		log.info("DAO동작 완료!! 서비스  -> 컨트롤러 이동" );
	}
//=========================================================================================
//로그인 메서드 재정의 
@Override
public MemberVO memberLogin(MemberVO vo) {
	//컨트롤러 -> 로그인정보(vo[id,pw]) -> 서비스
	log.info("memberServiceImpl: memberLogin(MemberVO vo)호출");
	
	log.info("VO:"+vo);
	//서비스 -> 로그인정보(vo[id,pw]) -> DAO
	log.info("DAO로그인 메서드 호출"); //Service에서 DAO메서드 호출 
	MemberVO loginVO = dao.loginMember(vo);
	
	log.info("loginVO: "+loginVO);

	return loginVO;
	//return dao.loginMember(vo)로도 사용 가능하지만 변수에 담는 것을 더 권장 
}
//=========================================================================================
//회원정보 조회 메서드 재정의
@Override
public MemberVO memberGet(String userid) {
	log.info("memberServiceImpl: memberGet(String userid)호출");
	//주입 객체 사용 -> 메서드 호출 
	MemberVO getVO = dao.getMember(userid);
	
	log.info("memberGet: "+getVO);
	
	return getVO;
}
}


