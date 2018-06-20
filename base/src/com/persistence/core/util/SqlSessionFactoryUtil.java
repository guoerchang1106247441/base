package com.persistence.core.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	
	private static SqlSessionFactory sessionfactory;
	
	static {
		try {
			sessionfactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader("resources/config/config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static SqlSessionFactory getSqlSessionFactory(){
		
		return sessionfactory;
	}
}
