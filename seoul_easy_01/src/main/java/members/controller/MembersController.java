package members.controller;
//회원가입9

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
import members.dto.AuthInfo;
import members.dto.ChangePwdCommand;
import members.dto.MembersDTO;
import members.service.MembersService;

// http://localhost:8090/myapp/member/signup.do

@Controller
public class MembersController {

	private MembersService membersService;
	
	public MembersController() {
	
	}
	
	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}
	
	//회원가입 폼
	//view페이지로 보내기(그냥 string으로 넘겨도 됨)
	@RequestMapping(value="/member/signup.do", method=RequestMethod.GET)
	public ModelAndView addMember(ModelAndView mav) {
		mav.setViewName("member/signup");//member폴더 안에 signup이 있어야함.(만들기)
		return mav;
	}
	
	//회원가입 처리
	//가입완료 클릭(signup.jsp 받아오기/serviceImp authInfo리턴한것 받기)
	@RequestMapping(value="/member/signup.do", method=RequestMethod.POST)
	public String addMember(MembersDTO membersDTO, HttpSession session) {
		AuthInfo authInfo = membersService.addMemberProcess(membersDTO);
		session.setAttribute("authInfo", authInfo);
		return "redirect:/home.do";
	}
	
	//로그아웃
	@RequestMapping(value="/member/logout.do")
	public String logoutMember(HttpSession session) {
		session.invalidate();//현재 세션에 저장돼 있는 모든 정보 제거
		return "redirect:/home.do";
	}
	
	//로그인 폼 불러오기(tiles.xml에 추가)
	@RequestMapping(value="member/login.do", method=RequestMethod.GET)
	public String loginMember() {
		return "member/login";
	}
	
	//로그인 처리
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String loginMember(MembersDTO membersDTO, HttpSession session, HttpServletResponse resp) {//아이디 비밀번호 받아와야함
		//membersDTO를 DB로 보내서 아이디 비번 맞는지 체크, 맞으면 가져와서 session에 저장시켜줌
		try {
			AuthInfo authInfo = membersService.loginProcess(membersDTO);
			session.setAttribute("authInfo", authInfo);//session에 저장함
			
			//이메일 기억하기 기능
			Cookie rememberCookie = new Cookie("REMEMBER", membersDTO.getMemberEmail());
			rememberCookie.setPath("/");
			
			if(membersDTO.isRememberEmail()) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 30); //60초 * 60분 * 24시간 * 30일 = 쿠키값을 1개월 저장해주겠다는 뜻
			}else {
				rememberCookie.setMaxAge(0);//0값을 주면 쿠키값이 삭제됨
			}
			
			resp.addCookie(rememberCookie);//응답할 때 쿠키값 같이 보내겠다
			
			return "redirect:/home.do";
		
		}catch(WrongEmailPasswordException e){
			resp.setContentType("text/html;charset=UTF-8");
			
			try {
				PrintWriter out = resp.getWriter();
				//out.print("아이디 비밀번호 불일치");
				out.print("<script>alert('아이디 비밀번호 불일치'); location.href='login.do';</script>");
				//out.print("<script>alert<'아이디 비밀번호 불일치'); history.go(-1);</script>");
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	//회원정보 수정 폼1
	@RequestMapping(value="/member/editmember.do", method=RequestMethod.GET)
	public ModelAndView updateMember(ModelAndView mav, HttpSession session) {
		//기존에 있는 정보가 미리 입력돼 있어야함.(비밀번호 제외)
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		mav.addObject("membersDTO", membersService.updateMemberProcess(authInfo.getMemberEmail()));
		mav.setViewName("member/editmember");
		return mav;
	}
		
	//회원정보 수정 처리4
	@RequestMapping(value="/member/editmember.do", method=RequestMethod.POST)
	public String updateMember(MembersDTO membersDTO, HttpSession session) {
		AuthInfo authInfo = membersService.updateMemberProcess(membersDTO);
		session.setAttribute("authInfo", authInfo);
		return "redirect:/home.do";
	}
	
	//비밀번호 변경 폼1 
	@RequestMapping(value="/member/changepass.do", method=RequestMethod.GET)
	public String changePassword() {
		return "member/changepass";
		
	}
	
	//비밀번호 변경 처리1
	@RequestMapping(value="/member/changepass.do", method=RequestMethod.POST)
	public String changePassword(ChangePwdCommand changePass, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		try {
			membersService.updatePassProcess(authInfo.getMemberEmail(), changePass);//service에 넘김
			return "redirect:/home.do";
		}catch(WrongEmailPasswordException e) {
			return "member/changepass";
			
		}
	}
	
}//end class
























