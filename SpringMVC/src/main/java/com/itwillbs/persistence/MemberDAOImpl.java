package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

@Repository
//=> 스프링(root-context.xml)에서 이 파일을 DAO파일로 인식하도록 설정 
public class MemberDAOImpl implements MemberDAO{
	//DB에 관련된 동작을 수행 
	
	//디비연결 정보 필요 -> 의존관계
	//sqlSessionFactory 객체 필요함(주입)
	@Inject
	private SqlSessionFactory factory;
	//이미 생성된 객체 [root-context.xml파일]-> sqlSessionFactory객체(Bean)을 주입 
	
	//==================================================================
	
	//디비연결 + MyBatis설정(mapper) + 자원해제 하는 객체 -> sqlSession
	@Inject
	private SqlSession sqlSession; //DI(dependence injection)
	
	//==================================================================
	
	//mapper의 주소(이름): 매퍼까지 도달하는 주소 
	private static final String NAMESPACE
				="com.itwillbs.mapper.MemberMapper";
	//매번쓰는 mapper주소를 상수화 시켜놓고 씀 
	
	//==================================================================
	
	//mylog단축키로 출력 
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	//==================================================================
	//select문으로 현재시간 반환하는 메서드 
	@Override
	public String getTime() {
		//1.2. 디비연결
		//3. sql작성
		//4. sql실행
		//5. 데이터 처리
		SqlSession sqlSession =  factory.openSession();
		//factory의 openSession메서드 이용하여 SqlSession객체 생성 
		
		//SqlSession객체 사용하여 쿼리문 select등 실행 
		String now = 
				sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		//com.itwillbs.mapper라는 패키지는 실존하지않음 -> 가상의 값
		//MemberMapper.xml 파일까지 찾아가게하는 가상의 값
		//.getTime -> MemberMapper에서 구분하는 이름 
		
		return now;
	}
	
	//==================================================================
	//회원가입 메서드
	@Override
	public void insertMember(MemberVO vo) {
		log.info("@@@ 1.2.디비연결 -sqlSession(DI객체)");
		log.info("@@@ 3.sql작성 -(memberMapper.xml)");
		log.info("@@@ 3.pstmt 객체 생성-sqlSession(DI객체)");
		log.info("@@@ 4.SQL실행 - sqlSession(DI객체)");
		log.info("@@@ 자원해제 -sqlSession(DI객체)");
		
		//MemberMapper에 쿼리문작성 한 뒤,
		sqlSession.insert(NAMESPACE+".insertMember", vo);
		//sqlSession.insert(SQL구문, 구문에 전달할 객체)
		
		//MemberMapper에 작성된 insert쿼리문의 전달되는 정보는 4개지만
		//vo변수 하나로 모든 쿼리문의 정보를 전달 가능 -> spring 편하다 
		//vo가 mapper로 이동하여 mapper의 SQL구문에 값을 부여해준다 
		
		//NAMESPACE+".insertMember" 
		//-> NAMESPACE :매퍼까지 감, 
		//-> + .insertMember(mapper에 정의된 insert쿼리구문의 id)
		log.info("@@@ 전달하는 VO객체는 mapper에서 자동으로 매핑 후 정보 전달");
		log.info("@@@ DAOImpl -> mapper이동()-> MYSQL이동 ");
		
		log.info("@@@ 자원해제 - sqlSession(DI객체)"); 
	}
	//==================================================================
	//로그인 메서드(객체 생성)
	@Override
	public MemberVO loginMember(MemberVO vo) {
		log.info("loginMember(vo) 호출");
		
		MemberVO resultVO
		//MembervO의 객체를 생성해서 사용 
			= sqlSession.selectOne(NAMESPACE+".loginMember",vo);
		//mapper에서 쿼리 실행 결과 저장해서 리턴 
		
		
		return resultVO;
	}
	//==================================================================
	//로그인 메서드2(객체 생성X)
	@Override
	public MemberVO loginMember(String userid, String userpw) {
		log.info("loginMember(userid,userpw) 호출");
		
		//mapper에 정보를 1개만 전달 가능 -> 로그인 메서드2의 매개변수는 2개인데..
		//sqlSession.selectOne(statement, parameter);(X)
		//그럼 어떻게 전달하지?
		
		//전달된 정보를 하나의 도메인 객체(vo)에 저장 후 처리 
		MemberVO vo = new MemberVO();
		vo.setUserid(userid);
		vo.setUserpw(userpw);

		sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		// 이 경우는, 로그인 메서드(로그인에 필요한 하나의 테이블 정보이기에,
		// 하나의 도메인에 담아 사용하는 것 가능)

		// 회원정보 + 게시판정보 => 하나의 도메인(MemberVO)에 저장하는 것? 불가능
		// 다른 테이블이기에 정보가 완전히 겹칠 수 없음
		// 이런경우, 컬렉션을 사용 : 연관없는 데이터를 한번에 저장할 수 있음
	// ------------------------------------------------------------------
		// Map 컬렉션 사용
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// Map(키값,벨류값) : 인터페이스 -> 객체생성 불가
		// Map을 구현하는 HashMap클래스로 객체생성

		// paramMap.put("컬럼명", 데이터값);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);

		sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap);

		return null;
	}
	//==================================================================
	//회원정보 조회 메서드 
	@Override
	public MemberVO getMember(String id) {
		
		log.info("getMember(String id) 호출 ");
		log.info("mapper-sql 구문 호출 동작 ");
		
		//("쿼리문",값) -> mapper의 쿼리문에서 값을 받아옴 
		MemberVO resultVO 
				= sqlSession.selectOne(NAMESPACE+".getMember",id);
		//mapper의 쿼리문 값과, DAO의 값은 테이블의 컬럼명으로 일치시키는 것이 좋다 
		//값이 하나일때는, 데이터타입에 따라 잘찾아오지만 값이 많아질 수록 혼란스러워질 수 있기 때문 
		
		log.info(resultVO+"");
		
		log.info("테스트 파일로 이동");
		
		
		return null;
	}
	//==================================================================
	//회원정보 수정 메서드 
	@Override
	public Integer updateMember(MemberVO uvo) {
		
		log.info("updateMember(MemberVO uvo) 호출");

		int result = sqlSession.update(NAMESPACE+".updateMember",uvo);
		log.info("회원 정보 수정 완료");
		
		//result= 0(업데이트 실패) or 1(업데이트 성공)
		log.info("updateMember -> 테스트 호출");
		
		return result;
	}
	//==================================================================
	//회원정보 삭제 메서드
	@Override
	public Integer deleteMember(MemberVO dvo) {
		log.info("deleteMember(MemberVO dvo)호출");
		
		int result = sqlSession.delete(NAMESPACE+".deleteMember",dvo);
		log.info("DAOImpl: 회원정보 삭제 완료"); 
		//result = 0(삭제 실패) or 1(삭제 성공)

		
		log.info("deleteMember -> 테스트 호출");
		
		return result;
	}
	//==================================================================
	//회원정보(리스트)조회 메서드
	@Override
	public List<MemberVO> getMemberList() {
		
		
		//DB에서 VO형태의 객체가 전달되면, 
		//List형태로 저장하는 객체 
		List<MemberVO> memberList 
			= sqlSession.selectList(NAMESPACE+".getMemberList");
		//1.DB의 정보 외에 추가적 정보 전달해야 할 때 쓰는 방식
		
		//return sqlSession.selectList(NAMESPACE+".getMemberList");
		//2.DB의 정보만 단순히 넘겨줄 때 쓰는 방식
		
		return memberList;
	}
}
