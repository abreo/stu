package cn;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.nihao.dao.LogindataMapper;
import com.nihao.dao.impl.CommonDao;
import com.nihao.model.Logindata;
import com.nihao.service.TestServiceI;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:spring.xml"})  
public class Tt {
	 @Autowired  
	 private TestServiceI service;
	 @Autowired
	 protected SqlSessionFactoryBean sqlSessionFactory;
	 @Autowired  
	 private LogindataMapper logindataMapper; 
	 @Autowired 
	 private CommonDao commonDao;
	 
	 @Test
	 public void tttttt(){
		 //List l=commonDao.selectListByParam("com.nihao.dao.UserMapper.selectAll", new HashMap());
		 //System.out.println(l);
		 RowBounds rb=new RowBounds(2, 2);
		 Long l=commonDao.countByParam("com.nihao.dao.LogindataMapper.countAll", new HashMap());
		 System.out.println(l);
		 List<Object> list=commonDao.selectListByParamPagenation("com.nihao.dao.LogindataMapper.selectList", new HashMap(), rb);
		 for(Object o:list){
			 Logindata lo=(Logindata)o;
			 System.out.println("---:"+lo.getId());
		 }
	 }


	 @Test
	 public void test() throws Exception{
		 String s=service.getTestById(1);
		 System.out.println(s);
		 
		 SqlSession session=SqlSessionUtils.getSqlSession(sqlSessionFactory.getObject());
		 PageBounds pageBounds = new PageBounds(1, 2);
		 List list=session.selectList("com.nihao.dao.UserMapper.selectAll",new HashMap(),pageBounds);
		 System.out.println(list.size());
		 
		 System.out.println(logindataMapper.countAll());
	 }
	
}
