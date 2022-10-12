package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
//=> 해당파일을 스프링(Junit)을 사용해서 테스트를 하도록 설정하는 것
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
		//=> 스프링 테스트할 때 필요한 설정을 위 경로에서 가져와 사용하겠다는 것 
public class MemberDAOTest {
	//DAO의 동작이 정상적으로 실행되는지 테스트하는 파일 
	
	
	//DAO객체 생성(직접생성->강한결합->X) 대신, 객체 주입
	//MemberDAO dao = new MemberDAO(); -> 인터페이스라 객체생성X
	//MemberDAOImpl daoImpl = new MemberDAOImpl(); (사용가능하지만, 강한결합 ->X)
	//MemberDAO dao = new MemberDAOImpl(); (사용가능하지만, 중간결합(인터페이스 업캐스팅)->X)
	
	@Inject
	private MemberDAO dao; //약한 결합 
	
	
	//콘솔에 문장찍기 위해 mylog로 로그문 
	private static final Logger log 
					= LoggerFactory.getLogger(MemberDAOTest.class);
	
	//@Test
	public void daoTest() {
		log.info("@@@dao:"+dao);
	}
	
	//@Test 
	public void 디비시간정보_조회() {
		log.info("@@@시간정보:"+dao.getTime());
	}
	
	//@Test
	public void 회원가입테스트() {
		log.info("@@@ 테스트 -> DAOImpl");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("itwill03");
		vo.setUserpw("1234");
		vo.setUsername("사용자");
		vo.setUseremail("itwill03@itwill.com");
		
		dao.insertMember(vo);
		//여기서(TEST) 실행하면 DB에 insert됨 
	}
	
	//로그인 테스트
	//@Test
	 public void 로그인로직_테스트() {
		 log.info("@@@ 로그인 체크(입력받은 정보를 DB값과 비교)");
		 
		 MemberVO vo = new MemberVO();
		 vo.setUserid("admin");
		 vo.setUserpw("1234");
		 
		 //MemberVO resultVO = dao.loginMember(vo);
		 //1. MemberImpl에서 객체생성하여 로그인 메서드 사용할 시 값을 대입하는 경우  
		 
		 MemberVO resultVO = dao.loginMember(vo.getUserid(),vo.getUserpw());		 
		 //2. MemberImpl에서 객체생성 없는 로그인 메서드 사용할 시 값을 대입하는 경우
		 
		 if(resultVO == null) {
			 log.info("회원정보 없음, 로그인 실패");
		 }else {
			 log.info("회원정보 있음, 로그인 성공!");
			 log.info(resultVO+"");
		 } 
		 
	 }
	
	 //회원정보 조회(아이디만 사용)
	 //@Test
	 public void 회원정보_조회() {
		 //dao객체 생성(=>객체 주입)
		 //회원정보조회 메서드 호출
		 MemberVO vo = dao.getMember("admin");
		 
		 //확인(출력)
		 if( vo != null) {
		 log.info("아이디 : "+vo.getUserid());
		 log.info("비밀번호 : "+vo.getUserpw());
		 log.info("이름 : "+vo.getUsername());		 
		 log.info("이메일 : "+vo.getUseremail());
		 log.info("가입일 : "+vo.getRegdate());
		 log.info("정보수정일 : "+vo.getUpdatedate());
		 }
	}
	 
	 //회원정보 수정
	 //-조건:아이디,비밀번호가 같을 때 이메일 수정(+updatedate도 수정)-> 업뎃했으니까~  
	 //@Test
	 public void 회원정보_수정() {
		 log.info("회원정보 수정(테스트 ->DAOImpl)");
		 
		 MemberVO uvo = new MemberVO();
		 //update쿼리문에 들어갈 정보 모두 기입 -> updatedate는 now() 
		 uvo.setUserid("admin"); //기존ID
		 uvo.setUserpw("1234"); //기존PW
		 uvo.setUseremail("Uadmin@Uadmin.com"); //수정할 이메일 
		 
		 dao.updateMember(uvo);
		 
		 int result = dao.updateMember(uvo);
		 if(result == 1) { // 1이면  -> 수정 성공 
			 log.info("회원정보 수정성공!");
		 }else { // result == 0 
			 log.info("회원정보 수정실패");
		 }
		 
	 }
	 
	 //회원정보 삭제
	 //@Test
	 public void 회원정보_삭제() {
		 log.info("회원정보 삭제 (테스트 -> DAOImpl)");
		 
		 MemberVO dvo = new MemberVO();
		 dvo.setUserid("itwill01");
		 dvo.setUserpw("1234");
		 
		 dao.deleteMember(dvo);
		 
		 int result = dao.deleteMember(dvo);
		 if(result == 1) { //1이면 -> 삭제 성공 
			 log.info("회원정보 성공 완료!");  
		 }else { //result == 0  -> 삭제 실패
			 log.info("회원정보 삭제 실패!");
		 }
	 }
	 
	 //회원목록(리스트)조회
	 //@Test
	 public void 회원목록리스트_조회() {
		 //DAO회원 목록 리스트 동작 호출 
		 List<MemberVO> memberList =  dao.getMemberList();
		 
		 //콘솔에 출력해보기  
		 for(MemberVO vo : memberList) {
			 //여러 컬럼 중 두개만 불러보기 
			 log.info("아이디 :"+vo.getUserid()+", 이메일:"+vo.getUseremail());
		 }
	 }
	
}
