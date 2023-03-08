package easyusers.dao;

import easyusers.dto.EasyusersDTO;

public interface EasyusersDAO {

	public int insertMember(EasyusersDTO dto);
	public EasyusersDTO selectByEmail(String email);
}
