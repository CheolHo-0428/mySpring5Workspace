package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	//JDBC 관련 변수
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	//SQL 명령어들
	private String USER_GET = "select * from USERS where id=? and password=?";	
	private String insertBoard = "insert into USERS values (?,?,?,?)";
	private String deleteBoard = "delete from USERS where = ?";
	private String updateBoard = 
			"update USERS set password=?, name=?, role=? where id=? ";
	
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		System.out.println("===> JDBC로 getUser() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
	}
		return user;
	}
}
