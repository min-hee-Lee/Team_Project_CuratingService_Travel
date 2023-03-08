package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import members.dto.AuthInfo;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{

	public AuthCheckInterceptor() {
		
	}
	
	//로그인 후 화면 보이기1
	
	//요청한 컨트롤러가 수행되기 직전 수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {//true값주면 정상 수행
			
		//로그인돼 있는지 체크(인증됐는지 확인)
		//false이면, 세션 없으면 그냥 null값 리턴
		//true 이면, 세션 없으면 세션 생성해 리턴
		HttpSession session = request.getSession(false);
		
			if(session != null) {
				AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
				if(authInfo != null) {
					return true;//로그인 된 상태
				}
			}
			//로그인이 안돼있으면 다시 로그인화면이 보이게
				response.sendRedirect(request.getContextPath() + "/member/login.do");
				return false;
			}
		
	
	
	
	//요청한 컨트롤러가 수행된 후 수행
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	//요청한 컨트롤러가 수행된 후 클라이언트에 뷰페이지 응답 직전 수행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}//end class























