<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


 <div class="container">
     <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">       
        <form class="validation-form" method="post" action="signup.do" novalidate >
      
           <div class="row">              
	         <div class="mb-3">
	            <label for="email">이메일</label>
	            <input type="text" class="form-control" name="email" 
	            		pattern="[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}"
	            		placeholder="예) you@example.com" required>
	            <div class="invalid-feedback">
	              이메일을 형식에 맞게 입력해주세요.
	            </div>
	          </div>
	          
	          <div class="mb-3">
	            <label for="easyuser_pass">비밀번호</label>
	            <input type="password"  class="form-control" name="easyuser_pass" 
	            pattern="[a-zA-Z0-9]{8,15}"  placeholder="영문,숫자 8~15자" required>
	            <div class="invalid-feedback">
	              비밀번호를 형식에 맞게 입력해주세요.
	            </div>
	          </div>  
	          
	          <!--           <div class="mb-3">
	            <label for="passChk">비밀번호 재입력</label>
	            <input type="text"  class="form-control" name="member_passChk" 
	            pattern="[a-zA-Z0-9]{8,15}" placeholder="영문,숫자 8~15자" required>
	           <div class="invalid-feedback">
	              비밀번호가 일치하지 않습니다.
	            </div> 
	          </div>  -->  
	          
	           <div class="mb-3">
	              <label for="easyuser_name">이름</label>
	              <input type="text" class="form-control" name="easyuser_name" 
	              pattern="[가-힣]{2,7}" placeholder="예) 홍길동 " required>
	              <div class="invalid-feedback">
	                이름을 형식에 맞게 입력해주세요.(한글 2~7자)
	              </div>
	            </div>  
	          
	          
	          
	             <div class="mb-3">
	              <label for="nick_name">닉네임</label>
	              <input type="text" class="form-control" name="nick_name" 
	              pattern="[가-힣]{2,7}" placeholder="예) 귀염둥이 " required>
	              <div class="invalid-feedback">
	                닉네임을 형식에 맞게 입력해주세요.(한글 2~7자)
	              </div>
	            </div>  
	          
	          
	                
	          <div class="mb-3">
	            <label for="phone_num">휴대전화 번호</label>
	            <input type="text" class="form-control" name="phone_num"  pattern="^010[0-9]{8}$" placeholder="예)01012345678" required>
	            <div class="invalid-feedback">
	              휴대전화 번호를 형식에 맞게 입력해주세요.
	            </div>
	          </div>
	          
	          
	             <div class="mb-3">
	            <label for="birth">생년월일</label>
	            <input type="text" class="form-control" name="birth"  pattern="[0-9]{8}" placeholder="예)20100806" required>
	            <div class="invalid-feedback">
	              생년월일을 형식에 맞게 입력해주세요.
	            </div>
	          </div>
	          
	          
	          
	              <div class="form-group">
                   <label for="sex">성별 </label>
                    <input type="radio" name="sex" value="M">남
                    <input type="radio" name="sex" value="F">여
                 </div>
	          
	          
	          
	
	          <hr class="mb-4">
	          <div class="custom-control custom-checkbox">
	            <input type="checkbox" class="custom-control-input" id="aggrement" required>
	            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
	          </div>
	          <div class="mb-4">
	          <input type="hidden" name="easyuser_type" value="1" />
	           <button class="btn btn-dark btn-lg btn-block" type="submit">가입 완료</button>
	           </div>
	        </div>
	        
	      </form>
	      </div>	   
    </div>  
</div> 

  <script >   
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
   
  </script>