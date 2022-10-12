package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlConnectTest {                                                                                             
	//테스트 클래스: 서버를 사용해서 테스트 하는 동작을 
	//			 junit을 사용해서 대신 테스트하는 클래스
	
	//================================================	
	//@Test : 테스트 동작을 수행하는 메서드에 사용 
	// 		  @Test가 있어야지만, junit이 실행 가능
	
	//@Test : 작성순서가 실행순서를 보장하지  X(랜덤)
	//================================================
	//@Before : 테스트 작업전에 반드시 준비(실행)되어야 하는 동작을 
	// 		       처리하는 어노테이션
	//================================================	
	//테스트에서 junit 처리 순서 : @Before -> @Test -> @After
	//================================================	
	//junit이 서버의 역할을 대신하며 톰캣이 멈춰져있어도 별도로 테스트 가능 
	//================================================	
	
	//log객체생성
	/*
	 * private static final Logger mylog
	 * =LoggerFactory.getLogger(MysqlConnectTest.class);
	 */
	//mylog 자동완성 사용
	private static final Logger log 
	= LoggerFactory.getLogger(MysqlConnectTest.class);
	
	//@Test
	@Before
	public void test() throws Exception{

		//test메서드 
		System.out.println("MysqlConnectTest - test()호출");
		System.out.println("테스트 클래스 실행!");
	}
	
	@Test
	public void 디비연결테스트() throws Exception{
		System.out.println("디비연결 테스트()수행 ");
		
		//1. 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println(" T : 드라이버 로드 성공 !");
		
		//2. 디비연결
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/springdb", "root", "1234");
		System.out.println(" T : 디비연결 성공!");
		System.out.println(" T : con : "+con);
		
		log.info("실행 테스트");
		
	}
	
	@Test
	public void 디비연결테스트2() throws Exception{
		System.out.println("디비연결 테스트2()수행 ");
		
		//1. 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println(" T : 드라이버 로드 성공 !");
		
		//2. 디비연결
		Connection con = null; //Connection 자원해제 위해 try문 밖에 선언
		//기존 try-catch구문 
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/springdb", "root", "1234");
			System.out.println(" T : 디비연결 성공!");
			System.out.println(" T : con : "+con);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close(); //자원해제
		}
		
		//try-with구문 -> finally없이도 자동으로 자원해제 됨 
		/*
		 * try(자원해제 필요한 객체 사용 -> Autocloseable 인터페이스 객체를 상속하는 객체){ 
		 * 	예외 발생할만한 코드 
		 * }
		 * catch{
		 * 
		 * }
		 */
		
		//try ()안에 con정의 
		try(Connection con1 = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/springdb", "root", "1234")) {
			
			System.out.println(" T : 디비연결 성공!");
			System.out.println(" T : con : "+con1);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}//finally문 없음 
		log.info("실행 테스트");
		
	}
	
}
