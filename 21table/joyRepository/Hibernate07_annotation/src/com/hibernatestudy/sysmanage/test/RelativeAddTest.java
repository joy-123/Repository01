package com.hibernatestudy.sysmanage.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.hibernatestudy.sysmanage.entity.Area;
import com.hibernatestudy.sysmanage.entity.Country;
import com.hibernatestudy.sysmanage.entity.Course;
import com.hibernatestudy.sysmanage.entity.Dept;
import com.hibernatestudy.sysmanage.entity.Student;
import com.hibernatestudy.sysmanage.entity.User;

/**
 * 类描述：多表的增、改方法，级联操作save-update
 * 项目名：Hibernate04_relative
 * 类名： com.hibernatestudy.sysmanage.test.RelativeAddTest
 * 作者： Joy
 * 时间：2017年3月10日 下午2:59:47
 */
public class RelativeAddTest {
	private Session session;
	
	@Before
	public void getSession() {
		//1:读取hibenrate.cfg.xml配置文件
		Configuration config=new Configuration().configure();//默认读取hibenrate.cfg.xml
		//Configuration configer = new Configuration().configure("xxx.cfg.xml");	//如果配置文件名非hibernate.cfg.XML	
		
		//2:创建sessionFactory
		SessionFactory sessionFactory=config.buildSessionFactory();
		
		//3:获取session
		session=sessionFactory.getCurrentSession();
	}
	
	//1
	@Test
	public void testSingleAdd(){			
 			try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			//5:增删改查操作
			//增加数据用于测试
			User user= new User();
			user.setUserName("蜡笔小新");
			user.setUserAge(5);
			user.setUserSex("boy");//属性不写入到数据库中
			session.save(user);
					
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}
	
	
	
	//*二、 一对多的单向关联：
	@Test
	public void testOne2Many(){			
 			try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			
			//5:增删改查操作
				//a:构造一个国家对象 
			 Country country = new Country();
			 country.setCountryName("oneCountry");
				//b:构造地区集合 
			 Area area1 = new Area();area1.setAreaName("manyArea1");
			 Area area2 = new Area();area2.setAreaName("manyArea2");
			 Area area3 = new Area();area3.setAreaName("manyArea3");
			 Set<Area> areaSet = new HashSet<Area>();
			 areaSet.add(area1);areaSet.add(area2);areaSet.add(area3);
			 	//c:将地区集合 赋值给country对象 
			 country.setAreaSet(areaSet);
			 	//用一方维护关联关系
			 session.save(country);
			 
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}
	
	
	//二、多对一的单向关联,一对多的双向关联
	@Test
	public void testMany2One(){			
 			try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			
			//5:增删改查操作
				//a:构造一个Area对象 
			 Area area = new Area();
			 area.setAreaName("manyAreaOther");
				//b:构造Country对象
			 Country country = new Country();
			 country.setCountryName("oneCountryOther");
			 	//c:将地区集合 赋值给Area对象 
			 area.setCountry(country);
			 	//用多方维护关联关系
			 session.save(area);
			 
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}	
	
	
	//三、自关联
	@Test
	public void testItselfRalative(){			
 			try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			
			//5:增删改查操作
			
			//从一对多的角度(父部门角度)维护  ：添加父部门时，添加子部门集合
				//a:构造一个Dept对象 
			 Dept pareDept=new Dept();	pareDept.setDeptName("顶级部门");		 
				//b:构造Dept集合		 
			 Dept childDept1 = new Dept();	childDept1.setDeptName("子部门1");
			 Dept childDept2 = new Dept();	childDept2.setDeptName("子部门2");
			 Set<Dept> childDeptSet = new HashSet<Dept>();
			 childDeptSet.add(childDept1); childDeptSet.add(childDept2);
			 	//c:将集合 赋值给pareDept对象 
			 pareDept.setChildDeptSet(childDeptSet);
			 	//用一方维护关联关系
			 session.save(pareDept);
	
	/*		//从多对一角度（子部门角度）维护 ：添加一个子部门同时添加一个父部门			
			Dept childDept= new Dept();childDept.setDeptName("子部门-多对一");
			
			Dept parentDept = new Dept();parentDept.setDeptName("父部门-多对一");
			childDept.setParentDept(parentDept);
			
			session.save(childDept);
			*/
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}	
	
	// 四、多对多：用student维护
	@Test
	public void testMany2ManyAdd(){			
 		try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			//5:增删改查操作
			Student student = new Student();
			student.setStName("小新");
			
			Course course1 = new Course();course1.setCourseName("bigdata");
			Course course2 = new Course();course2.setCourseName("java");
			Set<Course> courseSet = new HashSet<Course>();
			courseSet.add(course1);courseSet.add(course2);
			
			student.setCourseSet(courseSet);
			
			session.save(student);//session.saveOrUpdate(student);
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}
	
	
	// 四、多对多：用Course维护：已经放弃维护权，则不能在中间表中添加进对应关系
	@Test
	public void testMany2ManyAddOther(){			
 		try{
			//4:开启事务
			session.beginTransaction();		
			//session.getTransaction().begin();
			//5:增删改查操作
			
			Course course = new Course();course.setCourseName("python");
			
			Student student1 = new Student();student1.setStName("瑶瑶11");
			Student student2 = new Student();student2.setStName("妞妞11");
			Student student3 = new Student();student3.setStName("哈哈11");
			Student student4 = new Student();student4.setStName("李四11");
			Set<Student> studentSet = new HashSet<Student>();
			studentSet.add(student1);studentSet.add(student2);
			studentSet.add(student3);studentSet.add(student4);
			
			course.setStudentSet(studentSet);
			
			session.save(course);
			
			//6：事务提交
			session.getTransaction().commit();
		}catch(Exception e){	
			e.printStackTrace();
			//7：事务回滚
			session.getTransaction().rollback();
		}	 
	}	
	
}






