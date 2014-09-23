package com.chu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chu.dao.domain.User;
import com.chu.service.UserService;

@Controller
@RequestMapping("/es")
public class EsController extends AbstractController{

	@Autowired
	private UserService userService;
	
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
	
	
}
