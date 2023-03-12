<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EasySeoul</title>
<link rel="stylesheet" href="resources/css/home.css">
<body>
	<!-- Header 영역 -->
	<div class="wrapper">
		<div class="wrap">
			<div class="top_area">
				<div class="logo_area">
					<a href="home.do"><img src="resources/img/logo.png"></a>
				</div>
				<div class="theme_area">
					<input type="button" class="theme_button" value="테마여행">
				</div>
				<div class="freestyle_area">
					<input type="button" class="freestyle_button" value="여행스타일">
				</div>
				<div class="notice_area">
					<input type="button" class="notice_button" value="공지사항">
				</div>
				<div class="login_area">
					<input type="button" class="login_button" value="로그인">
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="content1_area">

				<span class="iam">나는</span>
				
				<form action="" method="post">
					<select name="info_theme"
						id="list">
						<option value="alone">나 홀로</option>
						<option value="kids">아이와 함께</option>
						<option value="parents">부모님과 함께</option>
						<option value="friends">친구과 함께</option>
					</select>
				</form>

				<form action="" method="post">
					<select name="freestyle"
						id="current">
						<option value="Exhibition">문화 탐방을 하는</option>
						<option value="Nature">자연을 즐기는</option>
						<option value="Attraction">체험할 수 있는</option>
						<option value="History">역사를 알아가는</option>
						<option value="ETC">추천에 따라</option>
					</select>
				</form>

				<span class="want"><br>여행을 즐기고 싶어요</span>

				<div class="main-sc2-btn">
					<div class="btn-type-round btn-reload">
						<input type="button" class="btn_reload" value="다른 취향 보기">
					</div>
					<div class="btn-type-round color--black btn-my-view">
						<input type="button" class="btn_black"
							value="&nbsp;&nbsp;&nbsp;내 취향 여행보기">
					</div>
				</div>

			</div>
			<!-- class="content1_area" -->


			<div class="content2_area"></div>
			
			
			<!-- Footer 영역 -->
			<div class="footer_nav">
				<div class="footer_nav_container"></div>
			</div>
			<!-- class="footer_nav" -->

			<div class="footer">
				<div class="footer_container">

					<div class="footer_left">
						<img src="resources/img/logo.png">
					</div>
					<div class="footer_right">
						Seoul Easy <br> <br> <strong>서울여행하세요~</strong>
					</div>

				</div>
			</div>
			<!-- class="footer" -->

		</div>
		<!-- class="wrapper" -->

	</div>
	<!-- class="wrapper" -->
</body>
</html>