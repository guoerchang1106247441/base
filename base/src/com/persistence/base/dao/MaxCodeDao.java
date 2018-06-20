package com.persistence.base.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.persistence.base.tool.kit.StringUtil;
import com.persistence.base.tool.kit.format.Format;
import com.persistence.core.util.SqlSessionFactoryUtil;

@Repository
public class MaxCodeDao {

	public String getUserMaxCode(){
		SqlSession session = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
		String sql = "com.persistence.base.mapper.MaxCodeMapper.getUserMaxCode";
		String code = session.selectOne(sql);
		if (StringUtil.isNull(code) || code == "") {
			code = "0";
        }
		long lMax = Long.parseLong(code) + 1;
		session.commit();
		session.close();
		return Format.format("000000", lMax);
	}
}
