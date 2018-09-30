package com.pier.controller;

import com.pier.bean.UserBean;
import com.pier.service.ILoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ILoginService loginServiceImpl;

	@RequestMapping("/index")
	public String index(){
		return "login";
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req, UserBean user) {
		log.info(user);

		ModelAndView mv = new ModelAndView();
		UserBean u = loginServiceImpl.Login(user.getUsername(),
				user.getPassword());

		if (u != null) {

			req.setAttribute("user", u);
			mv.addObject("password", u.getPassword());
			System.out.println(u.getPassword());
		}
		mv.setViewName("index");
		return mv;
	}
}