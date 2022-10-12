package com.itwillbs.web;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MyBatisTest {
	//DB연결과 Mybatis설정 제어할 수 있어야 함 -> 각각 필요함 -> 의존관계
	
	private static final Logger log 
    = LoggerFactory.getLogger(MyBatisTest.class);
	
	//의존관계(DI)
	//@Inject -> sqlFactory도 디비 연결정보를 가지고 있기에 주석처리
	private DataSource ds; //디비 연결정보 
	@Autowired
	private SqlSessionFactory sqlFactory; //디비 연결정보 +mybatis설정 
	//어노테이션 아무거나 사용 가능 
	
	@Test
	public void 객체주입여부_확인() {
		log.info("@@@ds : "+ds);
		log.info("@@@sqlFactory : "+sqlFactory);
	}
	
	@Test
	public void 디비연결테스트() {
		SqlSession sqlSession =  sqlFactory.openSession(); //디비연결 
		
		log.info("@@@sqlSession: "+sqlSession);
		//sql 쿼리에 대해 insert/select/commit 등 다양한 활동을 수행할 수 있는 객체 -> sqlSession
		//sqlSession.insert(statement);
		//sqlSession.select(statement, handler);
		//sqlSession.commit();
	}
}
