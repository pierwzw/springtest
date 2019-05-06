package com.pier.controller;

import com.pier.bean.User;
import com.pier.ILoginService;
import com.pier.util.CacheUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ILoginService loginServiceImpl;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/index")
	public String index(){
		return "login";
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req, User user) {
		log.info(user);

		ModelAndView mv = new ModelAndView();
		String key = "login:"+user.getUsername()+":"+user.getPassword();
		String account = (String) CacheUtil.get(redisTemplate, key);
		if (account != null){
			User tmp = new User();
			tmp.setUsername(user.getUsername());
			tmp.setAccount(Double.valueOf(account));
			HttpSession session = req.getSession();
			//session.setAttribute("user", tmp);
			//req.setAttribute("user", tmp);
			mv.addObject("user", tmp);
			log.info("username:" + user.getUsername() + " has login now");
			mv.setViewName("index");
			return mv;
		} else {
			User u = loginServiceImpl.login(user.getUsername(), user.getPassword());

			if (u != null) {
				CacheUtil.set(redisTemplate, key, ""+u.getAccount());
				mv.addObject("user", u);
				log.info("username:" + user.getUsername() + " has login now");
				mv.setViewName("index");
				return mv;
			} else {
				log.info("login failed, has no such user!");
				mv.addObject("msg","login failed, has no such user");
				mv.setViewName("error");
				return mv;
			}
		}
	}
}