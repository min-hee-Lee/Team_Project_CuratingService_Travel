<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand navbar-dark bg-dark">

	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" class="nav-link" href="${pageContext.request.contextPath}/home.do">SeoulEasy</a>
		</div>
	</div>
	
	<div>
		<ul class="navbar-nav mr-auto">
			<c:choose>
				<c:when test="${sessionScope.authInfo == null}" ><!-- 로그인이 안된 상태이면 -->
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/easyuser/login.do">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/easyuser/signup.do">회원가입</a></li>
				</c:when>
				
				<c:otherwise><!-- 로그인이 된 상태이면 보일 것 -->
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/easyuser/logout.do">${sessionScope.authInfo.easyuser_name}님 로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/easyuser/editmember.do">마이페이지</a></li>
				</c:otherwise>
				
			</c:choose>

		</ul>
	</div>
</nav>







