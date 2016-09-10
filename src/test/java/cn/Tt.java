package cn;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nihao.dao.LogindataMapper;
import com.nihao.dao.impl.CommonDao;
import com.nihao.model.Logindata;
import com.nihao.model.view.ResourceVO;
import com.nihao.service.ResourceServiceI;
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
	 @Autowired 
	 private ResourceServiceI resourceService;
	 
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
		 List<ResourceVO> list=resourceService.selectListByRoleId(0);
		 for(int i=0;i<list.size();i++){
			 ResourceVO vo=list.get(i);
			 System.out.println("a:"+vo.getId()+":"+vo.getResourcename());
			 List<ResourceVO> li=vo.getChildren();
			 if(li!=null){
				 for(ResourceVO v:li){
					 System.out.println("     b:"+v.getId()+":"+v.getResourcename());
				 }
			 }
		 }
	 }
	
}
