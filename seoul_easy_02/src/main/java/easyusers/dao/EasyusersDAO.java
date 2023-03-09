package easyusers.dao;

import easyusers.dto.EasyusersDTO;

public interface EasyusersDAO {

	public int insertEasyuser(EasyusersDTO dto);
	public EasyusersDTO selectByEmail(String email);
	
	public void updateEasyuser(EasyusersDTO dto); //회원정보 수정
	public void updateByPass(EasyusersDTO dto); //비밀번호 수정
}
