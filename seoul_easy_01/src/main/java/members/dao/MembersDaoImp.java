package members.dao;
//회원가입4
import org.apache.ibatis.session.SqlSession;

import members.dto.MembersDTO;

public class MembersDaoImp implements MembersDAO{

	private SqlSession sqlSession;
	
	public MembersDaoImp() {
		
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertMember(MembersDTO dto) {
		return sqlSession.insert("members.insertMember", dto);
	}

	@Override
	public MembersDTO selectByEmail(String memberEmail) {
		return sqlSession.selectOne("members.selectByEmail", memberEmail);
	}

	//회원정보수정처리2
	@Override
	public void updateMember(MembersDTO dto) {
		sqlSession.update("members.updateMember", dto);
		
	}

	//비밀번호 변경 처리5
	@Override
	public void updateByPass(MembersDTO dto) {
		sqlSession.update("members.updateByPass", dto);
		
	}
}
