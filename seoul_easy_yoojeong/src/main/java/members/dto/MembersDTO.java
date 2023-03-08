package members.dto;

import common.exception.WrongEmailPasswordException;

//회원가입1
public class MembersDTO {
	
	//members테이블 만든 것과 같은 이름으로 
	private String memberEmail;  //이메일
	private String memberPass; //비밀번호
	private String memberName; //이름
	private String memberPhone; //전화번호
	private int memberType; //회원구분 일반회원1, 관리자2
	private boolean rememberEmail; //자동 로그인 
	
	public MembersDTO() {
		
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPass() {
		return memberPass;
	}

	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}

	//로그인 시 입력한 비번과 DB상 비번이 맞는지 확인
	public boolean matchPassword(String memberPass) {//입력한 비번
		return this.memberPass.equals(memberPass);//DB에서 보내준 비번이 equal인지, match되면 true 아니면 false
	}
	
	//비밀번호 변경처리3
	//기존에 비번과 새로 입력한 비번을 비교
	public void changePassword(String oldPassword, String newPassword) {
		if(!memberPass.equals(oldPassword))//본인확인위해 기존 비밀번호와 같은 번호를 입력했는지 확인
			throw new WrongEmailPasswordException();//다르면 exception띄움
		this.memberPass=newPassword;//같으면 새로 입력한 비번으로 바꿔줌
	
	}
}














