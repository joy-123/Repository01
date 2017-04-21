hibernate的注解

	目的: 使用注解来完成 持久化对象与表 ，持久化对象属性与表字段的映射关系
	注意: 1:hibernate的注解直接使用JPA的注解，加上hibernate自己注解来补充
			大部分注解使用的是javax.persistence.Transient包中的注解标签
			（hibernate是实现了JPA接口的ORM组件，ORM是关系映射的思想，JPA是ORM思想的接口）
		 2:用了注解就可以省略持久化对象的映射文件
		 3:在hbm.cfg.xml文件不需要包含映射文件 ，但是要包含持久化对象类
		 4：注意大部分注解使用的是javax.persistence.Transient包中的注解标签
		5：@GeneratedValue(strategy=GenerationType.SEQUENCE) //主键生成策略
		如果要用native等主键生成策略，要用hibernate的标签如下： 
		   @GeneratedValue(generator="hibS")
		   @GenericGenerator(name="hibS",strategy="native")
		6：属性与表字段的映射注解最好不空行
	    7：在关联关系描述中注解配置了mapped属性，代表一方放弃维护权，同时将joinColumn注解去掉（多对多双向关联的注解，必须要求有一方放弃维护权，所以多对多中没有joinColumn）
		 	在一对多的关系中，只有一方能放弃维护权。
hibernate注解的几个方面
		（单对象）
	*　单对象的注解
		（关联对象）
	* 一对多的双向关联注解
	* 自关联注解
	* 多对多关联注解	

搭建hibernate时已经把注解用的包引进来了	
注解步骤：new 持久化对象-->持久化对象中注解( 持久化对象与表,属性与表字段,关联关系的映射)->
	-->在主配置文件中加入注解的持久化对象--》测试

主配置文件中如：<mapping class="com.hibernatestudy.sysmanage.entity.User"/>
	
一、	*　单对象的注解  	
	@Entity //声明是持久化对象
	@Table(name="T_USER")  //该持久化对象映射的表
	public class User {
		@Id 
		@GeneratedValue(strategy=GenerationType.SEQUENCE) //主键生成策略
		@Column(name="USER_ID") //属性与表字段的映射
		private Integer userId;
		@Basic//表明当前属性将持久化到数据库中  默认可以不写
		@Column(name="USER_NAME")
		@Transient //表明当前属性不写入到数据库中 （不会放到表结构中）
		private String userSex; 
		
		setter和getter方法
	}

二、一对多关联对象的注解
* 一对多的单向关联注解
	两方对象的注解，其中维持关系的一方多配置关联关系的映射如下：
	关联关系的映射
	@OneToMany(targetEntity=Area.class,cascade=CascadeType.ALL)//关联目标类（可省，但不建议），级联操作
	@JoinColumn(name="A_COUNTRY_ID") //外键（/添加的列）
	private Set<Area> areaSet;
	
	注意：如果列属性中有多余的属性不需要写入数据库中，需要@Transient注解或者删除属性否则报错建列
	
	//当onetomany注解配置了mapped属性，代表一方放弃维护权，同时将joinColumn注解去掉
	//country放弃了维护权后，session.save(country)时会报错
	@OneToMany(targetEntity=Area.class,cascade=CascadeType.ALL,mappedBy="country")//关联目标类（可省，但不建议），级联操作,放弃维护权
	private Set<Area> areaSet;

* 一对多的双向关联注解
	一对多的双向关联注解=一对多的单向+多对一的的单向，即两方都配置 关联关系的映射


三、* 自关联注解
	=一对多+多对一
	
四、* 多对多关联注解	
	=2个多对多
	持久化对象中注解( 持久化对象与表,属性与表字段,关联关系的映射(关联目标类，级联操作)&不用外键  )
	注意：
		1：多对多关联注解中关联关系的映射只需要注解(关联目标类，级联操作)，不用外键
		2：自动生成表：除了生成这两个持久化对象的表外，生成一个新的关联表，表明为两表名组合成。
		3：多对多双向关联的注解，必须要求有一方放弃维护权，否则，会生成两个中间表
			测试用已经放弃维护权 的一方维护：，则不能在中间表中添加进对应关系
		在关联关系描述中注解配置了mapped属性，代表一方放弃维护权，同时将joinColumn注解去掉（多对多双向关联的注解，必须要求有一方放弃维护权，所以多对多中没有joinColumn）
	SELECT * FROM STUDENT
	SELECT * FROM COURSE
	SELECT * FROM STUDENT_COURSE


五、spring 与hibernate的整合中
	applicationContext.xml配置 映射文件路径改为  引入注解的持久化类/要扫描的包：
	比如：
	<property name="packagesToScan">
		<list>
			<value>com.joy.hibernatestudy.sysmanage.entity</value>
		</list>
	</property> 
	 配置文件、路径用"/"如：classpath:com/joy/sss/sysmanage/entity
	而包用"."如：com.joy.hibernatestudy.sysmanage.entity
	
	详见;struts2_spring_hibernate
	
	
	
	















