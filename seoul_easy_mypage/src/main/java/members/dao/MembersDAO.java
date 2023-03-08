package members.dao;
//회원가입3
import members.dto.MembersDTO;

public interface MembersDAO {
	
	public int insertMember(MembersDTO dto);
	public MembersDTO selectByEmail(String memberEmail);
	
	public void updateMember(MembersDTO dto);//회원정보 수정을 위해서
	public void updateByPass(MembersDTO dto);//비밀번호 변경을 위해서
	

}
