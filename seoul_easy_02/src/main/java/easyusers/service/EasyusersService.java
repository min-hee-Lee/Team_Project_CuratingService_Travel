package easyusers.service;

import easyusers.dto.AuthInfo;
import easyusers.dto.ChangePwdCommand;
import easyusers.dto.EasyusersDTO;

public interface EasyusersService {
	
	public AuthInfo addEasyuserProcess(EasyusersDTO dto);
	public AuthInfo loginProcess(EasyusersDTO dto);
	
	public EasyusersDTO updateEasyuserProcess(String email);
	public AuthInfo updateEasyuserProcess(EasyusersDTO dto);
	public void updatePassProcess(String email, ChangePwdCommand changePwd);

}
