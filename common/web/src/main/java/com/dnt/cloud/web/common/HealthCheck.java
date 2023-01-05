package com.dnt.cloud.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheck {

	@GetMapping(value = "/_health_check")
	@ResponseBody
	public String healthCheck() {
		// return "redirect:health";
		// return "forward:health";
		return "ok";
	}

}
