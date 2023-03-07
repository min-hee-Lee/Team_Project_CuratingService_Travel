package members.dao;

import members.dto.MembersDTO;

public interface MembersDAO {

	public int insertMember(MembersDTO dto);
	public MembersDTO selectByEmail(String memberEmail);
}
