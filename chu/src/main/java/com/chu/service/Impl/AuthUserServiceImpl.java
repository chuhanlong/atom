package com.chu.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.Page;
import utils.PageUtils;

import com.chu.dao.domain.AuthUser;
import com.chu.dao.domain.AuthUserExample;
import com.chu.dao.mapper.AuthUserMapper;
import com.chu.service.AuthUserService;

@Service
public class AuthUserServiceImpl implements AuthUserService {

	private Logger log = Logger.getLogger(AuthUserServiceImpl.class);
	
	@Autowired
	private AuthUserMapper authUserMapper;
	
	public Page<AuthUser> findByMtime(String mtime, Page<AuthUser> page) {
		AuthUserExample c = new AuthUserExample();
		AuthUserExample.Criteria ca = c.createCriteria();
		if (StringUtils.isNotBlank(mtime)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date time = null;
			try {
				time = df.parse(mtime);
			} catch (ParseException e) {
				log.warn(ExceptionUtils.getFullStackTrace(e));
			}
			ca.andMtimeGreaterThanOrEqualTo(time);
		}
		if (page == null) {
			page = new Page<AuthUser>(10);
		} else {
			PageUtils.setPageToExample(page, c);
		}
		List<AuthUser> list = authUserMapper.selectByExample(c);
		int total = authUserMapper.countByExample(c);
		page.setTotalCount(total);
		page.setResult(list);
		return page;
	}

}
