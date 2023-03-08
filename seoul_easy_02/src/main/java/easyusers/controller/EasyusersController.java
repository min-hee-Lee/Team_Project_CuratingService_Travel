package easyusers.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import easyusers.dto.AuthInfo;
import easyusers.dto.EasyusersDTO;
import easyusers.service.EasyusersService;

//http://localhost:8090/myapp/easyuser/signup.do

@Controller
public class EasyusersController {

	private EasyusersService easyusersService;
	
	public EasyusersController() {

	}

	public void setEasyusersService(EasyusersService easyusersService) {
		this.easyusersService = easyusersService;
	}
	//회원가입 폼
	@RequestMapping(value="/easyuser/signup.do", method=RequestMethod.GET)
	public ModelAndView addEasyuser(ModelAndView mav) {
		mav.setViewName("easyuser/signup");
		return mav;
	}
	
	//회원가입 처리
	@RequestMapping(value="/easyuser/signup.do", method=RequestMethod.POST)
	public String addEasyuser(EasyusersDTO easyusersDTO, HttpSession session) {
		AuthInfo authInfo = easyusersService.addEasyuserProcess(easyusersDTO);
		session.setAttribute("authInfo", authInfo);
		return "redirect:/home.do";
	}
}
