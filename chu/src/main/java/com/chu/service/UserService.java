package com.chu.service;

import com.chu.dao.domain.User;

public interface UserService {

	/**
	 * save
	 * @param id
	 * @param name
	 * @return
	 */
	int save(Integer id,String name);
	
	/**
	 * 通过ID查询用户
	 * @param id
	 * @return
	 */
	User findById(Integer id	);
}
