package com.pier.controller;

import com.pier.ILoginService;
import com.pier.bean.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ILoginService loginServiceImpl;

	@RequestMapping("/register")
	public String register(){
		return "register";
	}

	@RequestMapping("/doregister")
	public ModelAndView doregister(HttpServletRequest req, User user) {
		log.info(user);

		ModelAndView mv = new ModelAndView();
		int count = 0;
		try {
			count = loginServiceImpl.register(user);
		} catch (Exception e) {
			log.error("register failed, username:" + user.getUsername() + ", password:" + user.getPassword(), e);
			mv.addObject("msg", "register failed!");
			mv.setViewName("error");
			return mv;
		}

		if (count == 1) {

			log.info("register successful, username:" + user.getUsername() + ", account:" + user.getAccount());
			mv.setViewName("login");
		}else {
			mv.addObject("msg", "register failed!");
			mv.setViewName("error");
		}
		return mv;
	}
}