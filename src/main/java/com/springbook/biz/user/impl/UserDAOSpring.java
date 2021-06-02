package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository("userDAOSpring")
public class UserDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String USER_GET = "select * from USERS where id=? and password=?";
	
	public UserVO getUser(UserVO vo) {
		Object[] args = {vo.getId(), vo.getPassword()};
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
}
