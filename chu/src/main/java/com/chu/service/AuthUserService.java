package com.chu.service;

import utils.Page;

import com.chu.dao.domain.AuthUser;

public interface AuthUserService {

	Page<AuthUser> findByMtime(String mtime, Page<AuthUser> page);
}
