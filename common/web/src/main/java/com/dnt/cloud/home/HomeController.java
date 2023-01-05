package com.dnt.cloud.home;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Resource
	Environment environment;

	@GetMapping("/")
	@ResponseBody
	public String index() {
		String appName = environment.getProperty("spring.application.name");
		return String.format("Welcome to %s!", appName);
	}

}