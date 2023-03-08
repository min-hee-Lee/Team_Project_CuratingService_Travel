<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- 게시판글쓰기1 -->

<!-- 제이쿼리 소스 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script>

$(document).ready(function(){
	$('#btnList').click(function(){
		$('#frm').attr('action', 'list.do');
		$('#frm').submit();
		//$('#frm').attr('action', 'list.do').submit();
	});
	
//게시판 글씨기저장8
$('#btnSave').click(function(){//저장버튼을 클릭하면
	
	$('[name=content]').val($('[name=content]').val().replace(/\n/gi, '<br/>'));//게시판 글쓰기에 줄바꿈 적용
	$('#frm').attr('action', 'write.do').submit();//이 페이지 출력
});

//첨부파일 용량 제한 알림창
	$('#filepath').change(function(){
		console.log(this.files[0].size);
		if(this.files[0].size>1000000000){
			alert('1GB 이하만 첨부할 수 있습니다.');
			$('#filepath').val('');
			return false;
		}
	});

});
</script>
    
<div class="container">
<!-- 첨부파일이 있는 글쓰기에는 반드시 이 form이 들어가야함 -->
	<form name="frm" id="frm" method="post"   
	      enctype="multipart/form-data" >

		<table class="table  table-bordere mt-1">			

			<tr>
				<td width="20%" align="center">writer</td>
				<td>
				<input type="hidden" name="memberEmail" value="${sessionScope.authInfo.memberEmail}" />
				<input type="text" name="memberName" size="30" maxlength="30" readonly="readonly" value="${sessionScope.authInfo.memberName}" /></td>
			</tr>

			<tr>
				<td width="20%" align="center">제목</td>
				<td>				
				 <c:if test="${dto.ref !=0}">답변</c:if> 		 
				<input type="text" name="subject" size="40" /></td>
			</tr>
 
			<tr>
				<td width="20%" align="center">내용</td>
				<td><textarea name="content" rows="13" style="width:100%"></textarea></td>
			</tr>

			<tr>
				<td width="20%" align="center">첨부파일</td>
				<td><input type="file" name="filename" id="filepath"/>
			</tr>
		</table>
		
		
		<!-- 답변글일때.... -->
		 <c:if test="${dto!=null}">
			<input type="text" name="num" id="num" value="${dto.num}" />
			<input type="text" name="currentPage" id="currentPage" value="${pv.currentPage}" />
			<input type="text" name="ref" value="${dto.ref}" />
			<input type="text" name="re_step" value="${dto.re_step}" />
			<input type="text" name="re_level" value="${dto.re_level}" />
		</c:if> 
		<div class="text-center mt-5 mb-5">
		   <input type="button" id="btnList" value="리스트" /> 
		   <input type="button" id="btnSave" value="저장" />
		</div>
	</form>
</div>


    