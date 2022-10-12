package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	//테스트용 메서드: DB시간조회 
	public String getTime();
	
	//회원가입 동작
	public void insertMember(MemberVO vo);
	
	//로그인 동작(객체 생성O)
	public MemberVO loginMember(MemberVO vo);
	
	//로그인 동작2(객체 생성X)
	public MemberVO loginMember(String userid, String userpw);
	
	//회원정보 조회
	public MemberVO getMember(String id);
	
	//회원정보 수정 (int 사용 시,레퍼런스타입으로 바꾸는 것이 데이터안정성에 좋음(형변환시))
	public Integer updateMember(MemberVO uvo);
	
	//회원정보 삭제
	public Integer deleteMember(MemberVO dvo);
	
	//회원목록(리스트)조회 (java util밑의 List사용)
	//제네릭이 없어도 되지만, 없으면 모두 Object데이터타입으로 받아오기때문에 
	//현재처럼 받아오는 데이터타입이 모두 하나인 경우 제네릭에 데이터타입 명시하기 
	public List<MemberVO> getMemberList();

}
