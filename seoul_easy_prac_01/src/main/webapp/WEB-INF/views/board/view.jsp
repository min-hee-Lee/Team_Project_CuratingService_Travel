<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 제이쿼리 소스 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- 게시글에서 리스트 클릭 -->
<script>
$(document).ready(function(){
	$('#list').click(function(){
		$('#frm').attr('action', 'list.do').submit();
	});
	
	<!-- 게시글에서 답변 클릭 -->	
	$('#answer').click(function(){
		$('#frm').attr('action', 'write.do').submit();//write.do:답변글, 제목글 같은 form사용
	});
	
	<!-- 수정1 -->
	$('#update').click(function(){
		$('#frm').attr('action', 'update.do').submit();
	});
	
	<!-- 삭제5 -->
	$('#delete').click(function(){
		$('#frm').attr('action', 'delete.do').submit();
	});
	
});
</script>


    <div class="container">
	<table class="table  table-bordered">
		<tr>
			<th width="20%">작성자</th>
			<td>${dto.membersDTO.memberName}</td>
			<th width="20%" >조회수</th>
			<td>${dto.readcount}</td>
		</tr>

		<tr>
			<th >제목</th>
			<td colspan="3">${dto.subject}</td>
		</tr>

		<tr>
			<th>내용</th>
			<td colspan="3" >${dto.content}</td>
		</tr>

		<tr>
			<th>파일</th>  
			<td colspan="3"><c:if test="${!empty dto.upload}">
					<a href="contentdownload.do?num=${dto.num}">
						${fn:substringAfter(dto.upload,"_")} </a>
				</c:if> <c:if test="${empty dto.upload }">
					<c:out value="첨부파일 없음" />
				</c:if></td>
		</tr> 
	</table>
   <div class="text-center mt-5 mb-5">
		<form name="frm" id="frm" method="get">
			<input type="hidden" name="num" value="${dto.num}" /> 
			<input type="hidden" name="currentPage" id="currentPage" value="${currentPage}" /> 
			
			<!-- 답변글 작성할 때 -->
			<c:if test="${dto.ref !=0}">
				<input type="hidden" name="ref" value="${dto.ref}" /> 
				<input type="hidden" name="re_step" value="${dto.re_step}" /> 
				<input type="hidden" name="re_level" value="${dto.re_level}" /> 
			</c:if>
			
			<input type="button" id="list" 	value="리스트" />		   
			<input type="button" id="answer" value="답변" />		
			
			<!-- 내가 올린 글에서만 수정, 삭제 버튼 보이기 -->	
			<c:if test="${sessionScope.authInfo != null && sessionScope.authInfo.memberEmail==dto.memberEmail}">	
				<input type="button" id="update" value="수정" /> 
				<input type="button" id="delete" value="삭제" />
			</c:if>
		</form>
	</div>
</div>





























    