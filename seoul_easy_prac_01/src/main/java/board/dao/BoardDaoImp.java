package board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import board.dto.BoardDTO;
import board.dto.PageDTO;

public class BoardDaoImp implements BoardDAO {

	private SqlSessionTemplate sqlSession;
	
	public BoardDaoImp() {
		
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
}

	//게시판2(레코드수 출력)
	@Override
	public int count() {
		return sqlSession.selectOne("board.count");
	}

	//게시판9
	@Override
	public List<BoardDTO> list(PageDTO pv) {
		return sqlSession.selectList("board.list", pv);
	}

	//게시판 조회수 증가
	@Override
	public void readCount(int num) {
		sqlSession.update("board.readCount", num);
	}

	//게시판 글 확인
	@Override
	public BoardDTO content(int num) {
		return sqlSession.selectOne("board.content", num);
	}

	//게시판 답변글
	@Override
	public void reStepCount(BoardDTO dto) {
		sqlSession.update("board.reStepCount", dto);
		
	}

	//게시판글쓰기저장6
	@Override
	public void save(BoardDTO dto) {
		sqlSession.insert("board.save", dto);
	}

	//첨부파일 수정2
	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("board.update", dto);
	}

	//삭제2
	@Override
	public void delete(int num) {
		sqlSession.delete("board.delete", num);
	}

	//첨부파일 수정5
	@Override
	public String getFile(int num) {
		return sqlSession.selectOne("board.getFile", num);
	}
	
}
