package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	//회원가입 
	public void memberJoin(MemberVO vo);
	
	//로그인 체크 -> 왜 리턴값 있는지? MemberDAO에서 MemberVO로 리턴하고 있기때문에(맞춰줘야 함)
	public MemberVO memberLogin(MemberVO vo);
	//public MemberVO memberLogin(String userid,Strung userpw); -> 이렇게 해도 상관없지만 MemberVo vo로 한 번에 받는것 편리
	
	//회원정보 조회 
	public MemberVO memberGet(String userid);
	
	
}
