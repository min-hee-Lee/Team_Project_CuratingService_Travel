package easyusers.dto;

public class EasyusersDTO {

	//테이블 변수명과 일치해야함
	private String email;
	private String easyuser_pass;
	private String easyuser_name;
	private String phone_num;
	private String sex;
	private int birth;
	private int easyuser_type;  //회원구분
	private int easyuser_num; // 회원번호
	private boolean rememberEmail; //자동로그인 기능
	
	public EasyusersDTO() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEasyuser_pass() {
		return easyuser_pass;
	}

	public void setEasyuser_pass(String easyuser_pass) {
		this.easyuser_pass = easyuser_pass;
	}

	public String getEasyuser_name() {
		return easyuser_name;
	}

	public void setEasyuser_name(String easyuser_name) {
		this.easyuser_name = easyuser_name;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public int getEasyuser_type() {
		return easyuser_type;
	}

	public void setEasyuser_type(int easyuser_type) {
		this.easyuser_type = easyuser_type;
	}

	public int getEasyuser_num() {
		return easyuser_num;
	}

	public void setEasyuser_num(int easyuser_num) {
		this.easyuser_num = easyuser_num;
	}

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}

	
	
	
}

