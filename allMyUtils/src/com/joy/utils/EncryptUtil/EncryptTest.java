package com.joy.utils.EncryptUtil;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Test;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 类描述：用于测试各种加密算法 
 * 类    名：com.joy.ssspm.test.EncryptTest
 * 2017年3月17日 下午3:41:23
 * 加密算法分为不可逆算法和可逆算法  common-codec包提供的几种加密算法，前提加common-codec包
	*　不可逆算法类型
		MD5 
		SHA1
	* 可逆算法
		BASE64
		HEX
 */
public class EncryptTest {
	//1、测试不可逆加密算法MD5
	@Test
	public void testMD5(){
		String plainPsd = "123456";
		System.out.println(DigestUtils.md5Hex(plainPsd.getBytes()));//common-codec包中的工具类提供的方法
		System.out.println(DigestUtils.md5Hex(plainPsd));
		//e10adc3949ba59abbe56e057f20f883e
	}
	
	//2、测试不可逆加密算法SHA1
	@Test
	public void testSha1(){
		String plainPsd = "123456";
		System.out.println(DigestUtils.sha1Hex(plainPsd));//string
		//7c4a8d09ca3762af61e59520943dc26494f8941b
	}
	
	/**
	 * 不可逆加密算法MD5和SHA1类似用法,common-codec包中的工具类
	 * DigestUtils.md5Hex(byte[]/String)
	 */
	
	
	//3-1、测试可逆加密算法BASE64  加密
	@Test
	public void testBASE64Encode(){
		String plainPsd = "123456";
		System.out.println(Base64.encode(plainPsd.getBytes()));
		//MTIzNDU2
	}
		
	//3-2、测试可逆加密算法BASE64  解密
	@Test
	public void testBASE64Decode() throws Base64DecodingException{
		String encryptPsd = "MTIzNDU2";
		System.out.println(new String(Base64.decode(encryptPsd.getBytes())));//new String()字节转化为string
		//123456
	}
	/**
	 *BASE64import security包
	 */
	
	//4-1 测试可逆加密算法HEX  加密
	@Test
	public void testHEXEncode(){
		String plainPsd = "123456";
		System.out.println(Hex.encodeHex(plainPsd.getBytes()));
		//313233343536
	}
		
	//4-2 测试可逆加密算法HEX  解密
	@Test
	public void testHexDecode() throws Base64DecodingException, DecoderException{
		String encryptPsd = "313233343536";
		System.out.println(new String(Hex.decodeHex(encryptPsd.toCharArray())));//encryptPsd.toCharArray()==》char[]		
		//123456
	}

	/**
	 * ===========业界通用加密算法：=====================================================================
	 */
	
	
	//用工具类EncryptUtil的方法加密	
		//1：生成一个随机数RANDOM  （盐，佐料）
		//2:用可逆的加密算法加密随机数 HEX.ENCODE(RANDOM)

		//3:将随机数和我们的密码 用sha1不可逆算法加密SHA1(RANDOM+password)
		//4：将第三步得到的字符串值用可逆的加密算法加密 HEX.ENCODE(3)
		//5：将第2步和第4步的值拼凑
		//
		//加密： encryptCode = HEX.ENCODE(RANDOM)+ HEX.ENCODE(SHA1(RANDOM+password))
		@Test
		public void testPmEncrypt(){
			String plainPsd = "123456";
			//1
			byte[] random =EncryptUtil.generateSalt(8);//几位的随机数
			//2
			String randomHex =EncryptUtil.encodeHex(random);
			
			//3
			byte[] sha1Psd=EncryptUtil.sha1(plainPsd.getBytes(), random, 1024);
			//4
			String hexPsd =EncryptUtil.encodeHex(sha1Psd);
			//5
			String encryptPsd =randomHex+hexPsd;
			
			System.out.println(encryptPsd);
			
			//一样的密码，每次加密的结果都不一样
			//c45169a5a60446fc371736ccdd16daf7877b1761f95da6e0637b99d0
			//aaa6b1718825bf5e0da7a463e5b3a2c5b9925ffe907989c758d578bd
		}
	//密码验证
	  //可逆加密算法HEX 加密后的位数是原来的两倍
 	@Test
 	public void testPsdValidator(){
 		String password = "123456";//页面的登陆密码
 		String encryptPsd = "c45169a5a60446fc371736ccdd16daf7877b1761f95da6e0637b99d0";//数据库中我的密码
 		//-5，-2 将密文逆转 ，截取 salt盐的明文   random8位==》2*8 randomHex = encryptUtil.encodeHex(random)
 		byte[] salt = EncryptUtil.decodeHex(encryptPsd.substring(0, 16));
 		 		
 		//3 重新拼凑 盐+密码   进行sha1的加密
 	    byte[] hashPass = EncryptUtil.sha1(password.getBytes(), salt, 1024);
		//4，5
 	    String newEcnryptPsd= EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashPass);
 	    System.out.println("--"+newEcnryptPsd);
 	}
	 	
/* 	@Test
 	  public void encyptPassword () {
 		String plainPassword="123456";
 			byte[] random =EncryptUtil.generateSalt(8);//几位的随机数
 			String randomHex =EncryptUtil.encodeHex(random);
 			byte[] sha1Psd=EncryptUtil.sha1(plainPassword.getBytes(), random, 1024);
 			String hexPsd =EncryptUtil.encodeHex(sha1Psd);
 			String encryptPsd =randomHex+hexPsd;
 			System.out.println("密文--"+encryptPsd);
 	    }
 	@Test
 	 public void validatePassword () {
 		String plainPsd="123456";
 		String encryptPsd="c45169a5a60446fc371736ccdd16daf7877b1761f95da6e0637b99d0";
  		//-5，-2 将密文逆转 ，截取 salt盐的明文 HEX加密后密文位数是原来的2倍：  random8位==》2*8 randomHex = encryptUtil.encodeHex(random)
  		byte[] salt = EncryptUtil.decodeHex(encryptPsd.substring(0, 16));	 		
  		//3 重新拼凑 盐+密码   进行sha1的加密
  	    byte[] hashPass = EncryptUtil.sha1(plainPsd.getBytes(), salt, 1024);
 		//4，5
  	    String newEcnryptPsd= EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashPass);
  	  System.out.println(encryptPsd.equals(newEcnryptPsd));
     	
     }*/
}
