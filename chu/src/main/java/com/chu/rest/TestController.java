package com.chu.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chu.dao.vo.ResObj;

@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping(value="/print",method = RequestMethod.POST)
	public ResObj<String> test(String str) {
		ResObj<String> res = new ResObj<String>();
		res.setBusinessObj(str);
		return res;
	}
	
}
