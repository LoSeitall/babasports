package com.oracle.sport.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/index.aspx")
	public String index(String ReturnUrl){
		return "login";
	}
}
