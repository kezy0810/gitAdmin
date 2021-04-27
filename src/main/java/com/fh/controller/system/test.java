package com.fh.controller.system;

import org.apache.shiro.crypto.hash.SimpleHash;

public class test {

	 public static void main(String args[]) { 

		    String USERNAME="admin";
		    String PASSWORD="Admin123";
			String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//密码加密
			System.out.println(passwd);
	 } 
	
	
	
}
