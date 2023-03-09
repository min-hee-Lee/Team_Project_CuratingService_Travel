package easyusers.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import common.exception.WrongEmailPasswordException;
import easyusers.dto.AuthInfo;
import easyusers.dto.ChangePwdCommand;
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
	
	//회원가입 하면 바로 로그인 되므로 로그아웃
	@RequestMapping(value="/easyuser/logout.do")
	public String logoutEasyuser(HttpSession session) {
		session.invalidate(); //현재 세션에 저장돼 있는 모든 정보 제거
		return "redirect:/home.do";
	}
	
	//로그인 폼
	@RequestMapping(value="easyuser/login.do", method=RequestMethod.GET)
	public String loginEasyuser() {
		return "easyuser/login";
	}
	
	//로그인 하기
	@RequestMapping(value="/easyuser/login.do", method=RequestMethod.POST)
	public String loginEasyuser(EasyusersDTO easyusersDTO, HttpSession session, HttpServletResponse resp) {
		try {
			AuthInfo authInfo = easyusersService.loginProcess(easyusersDTO);
			session.setAttribute("authInfo", authInfo);
			
			//이메일 기억하기
			Cookie rememberCookie = new Cookie("REMEMBER", easyusersDTO.getEmail());
			rememberCookie.setPath("/");
			
			if(easyusersDTO.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30); 
			}else {
				rememberCookie.setMaxAge(0);
			}
			
			resp.addCookie(rememberCookie);
			
			return "redirect:/home.do";
		}catch(WrongEmailPasswordException e) {
			resp.setContentType("text/html;charset=UTF-8");
			
			try {
				PrintWriter out = resp.getWriter();
				out.print("<script>alert('아이디, 비밀번호가 일치하지 않습니다.'); location.href='login.do';</script>");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	//회원정보 수정 폼
	@RequestMapping(value="/easyuser/editmember.do", method=RequestMethod.GET)
	public ModelAndView updateEasyuser(ModelAndView mav, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		mav.addObject("easyusersDTO", easyusersService.updateEasyuserProcess(authInfo.getEmail()));
		mav.setViewName("easyuser/editmember");
		return mav;
	}
	
	//회원정보 수정 처리
	@RequestMapping(value="/easyuser/editmember.do")
	public String updateEasyuser(EasyusersDTO easyusersDTO, HttpSession session) {
		AuthInfo authInfo = easyusersService.updateEasyuserProcess(easyusersDTO);
		session.setAttribute("authInfo", authInfo);
		return "redirect:/home.do";
	}
	
	//비밀번호 변경 폼
	@RequestMapping(value="/easyuser/changepass.do", method=RequestMethod.GET)
	public String changePassword() {
		return "easyuser/changepass";
	}
	
	//비밀번호 변경 처리
	@RequestMapping(value="easyuser/changepass.do", method=RequestMethod.POST)
	public String changePassword(ChangePwdCommand changePass, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		try {
			easyusersService.updatePassProcess(authInfo.getEmail(), changePass);
			return "redirect:/easyuser/editmember.do";
		} catch(WrongEmailPasswordException e) {
			
			return "easyuser/changepass";
		}
		
	}
}














