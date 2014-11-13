package com.chu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.Page;

import com.chu.dao.domain.AuthUser;
import com.chu.dao.domain.User;
import com.chu.dao.vo.ResObj;
import com.chu.service.AuthUserService;
import com.chu.service.EsService;
import com.chu.service.UserService;

//@Controller
@RequestMapping("/es")
public class EsController extends AbstractController{

	@Autowired
	private UserService userService;
	@Autowired
	private AuthUserService authUserService;
	@Autowired
	private EsService esService;
//	@Autowired
//	private EsShopInfoService esShopInfoService;
	
	@ResponseBody
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public User test1() {
		User user = new User();
		user.setId(1);
		user.setName("test");
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/search/{id}",method=RequestMethod.POST)
	public User findById(@PathVariable Integer id) {
		User user = null;
		user = userService.findById(id);
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value="/importdata/index",method=RequestMethod.POST)
	public ResObj<Integer> importIndexData(String mtime) {
		ResObj<Integer> res = new ResObj<Integer>();
		Page<AuthUser> page = new Page<AuthUser>();
		authUserService.findByMtime(mtime, page);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/importdata/db",method=RequestMethod.POST)
	public ResObj<Integer> importDbData(String mtime) {
		ResObj<Integer> res = new ResObj<Integer>();
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value="/importdata",method=RequestMethod.POST)
	public ResObj<Integer> importDbData() {
		ResObj<Integer> res = new ResObj<Integer>();
		
		return res;
	}
}
