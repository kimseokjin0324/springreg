package com.hompage.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	private static final Logger logger=LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute("msg","홈페이지 방문을 환영합니다.");
		return "main";
	}
	//url mapping
	//기본, 루트페이지=>home 메서드 호출
	@RequestMapping(value ="home.do",method=RequestMethod.GET)
	public String home(Locale locale,Model model) {
		logger.info("여기는 home",locale);
		
		Date date=new Date();
		DateFormat dateFormat=DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,locale);
		String formattedDate=dateFormat.format(date);
		
		//모델(서블릿의 request 객체를 대체한것)
		model.addAttribute("servierTime",formattedDate);
		return "home";
		

	}
	
}
