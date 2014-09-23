package com.chu.service.Impl;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chu.dao.domain.User;
import com.chu.dao.domain.UserExample;
import com.chu.dao.mapper.UserMapper;
import com.chu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public int save(Integer id, String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		int r = userMapper.insert(user);
		return r;
	}

	public User findById(Integer id) {
		User user = null;
		if (id != null) {
			UserExample c = new UserExample();
			UserExample.Criteria ca = c.createCriteria();
			ca.andIdEqualTo(id);
			List<User> list = userMapper.selectByExample(c);
			if (CollectionUtils.isNotEmpty(list)) {
				user = list.get(0);
			}
		}
		return user;
	}

}
