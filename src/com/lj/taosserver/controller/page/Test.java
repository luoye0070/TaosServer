package com.lj.taosserver.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test {
	@RequestMapping("one")
	public String testone(){
		
		return "one";
	}
}
