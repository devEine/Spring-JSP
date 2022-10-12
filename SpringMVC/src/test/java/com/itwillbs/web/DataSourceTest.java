package com.itwillbs.web;



import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//-> '이 클래스를 스프링모드로 테스트 하겠습니다' 라는 뜻
@ContextConfiguration(
//-> 프로젝트 실행할 때 사용할 설정, 위치: ~~~~ 
   		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
//=======================================================================================
public class DataSourceTest {
	//DataSource 객체 생성여부 확인
	private static final Logger log = LoggerFactory.getLogger(DataSourceTest.class);

//=======================================================================================	
	//DataSource객체 생성(직접생성 - 강한결합  => 주입으로 변경)
	
	//@inject : Spring에 있는 객체(빈)을 가져와서 주입하겠다. 
	//		      객체 직접 생성X, 의존관계 주입 
	//         => DI(Dependency Injection)
	//@Inject -> 이것도 실행되고 
	//@Autowired // -> 이것도 실행됨 
	//@inject 와 @aurtowired의 차이점 
	//@inject -> java에서 제공
	//@autowired -> Springframework에서 제공 
	@Inject
	private DataSource ds; //new DataSource();
	//javax.sql의 Datasource 
	
//=======================================================================================
	@Test
	public void DataSource있는지() {
		//DataSource 객체가 필요 => 객체를 의존하고 있음(의존관계)
	log.info(ds.toString());
	log.info(ds+""); //""넣으면 -> String타입이 됨 
	
		
	}
//=======================================================================================
	@Test
	public void 디비연결되는지() {
		//디비연결 체크 
		try {
			Connection con = ds.getConnection();
			log.info("디비연결성공!!");
			log.info(con+"");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
