package easyusers.dao;

import org.apache.ibatis.session.SqlSession;

import easyusers.dto.EasyusersDTO;

public class EasyusersDaoImp implements EasyusersDAO{
	
	private SqlSession sqlSession;
	
	public EasyusersDaoImp() {
		
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertMember(EasyusersDTO dto) {
		return sqlSession.insert("easyusers.insertMember", dto);
	}
	
	@Override
	public EasyusersDTO selectByEmail(String email) {
		return sqlSession.selectOne("easyusers.selectByEmail", email);
	}
	
}
