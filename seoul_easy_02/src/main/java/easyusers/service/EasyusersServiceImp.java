package easyusers.service;

import easyusers.dao.EasyusersDAO;
import easyusers.dto.AuthInfo;
import easyusers.dto.ChangePwdCommand;
import easyusers.dto.EasyusersDTO;

public class EasyusersServiceImp implements EasyusersService{

	private EasyusersDAO easyusersDao;
	
	public EasyusersServiceImp() {

	}

	public void setEasyusersDao(EasyusersDAO easyusersDao) {
		this.easyusersDao = easyusersDao;
	}
	
	//회원가입 후 바로 로그인 처리하기
	@Override
	public AuthInfo addEasyuserProcess(EasyusersDTO dto) {
		easyusersDao.insertMember(dto);
		return new AuthInfo(dto.getEmail(), dto.getEasyuser_name(), dto.getEasyuser_pass());
	}

	//로그인하기
	@Override
	public AuthInfo loginProcess(EasyusersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyusersDTO updateEasyuserProcess(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthInfo updateEasyuserProcess(EasyusersDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEasyuser_passProcess(String email, ChangePwdCommand changePwd) {
		// TODO Auto-generated method stub
		
	}
}
