package board.service;

import java.io.File;
import java.util.List;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import board.dto.PageDTO;

public class BoardServiceImp implements BoardService{
	public BoardDAO boardDao;
	
	public BoardServiceImp() {
		
	}
	
	public void setBoardDao(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}

	//게시판3
	@Override
	public int countProcess() {
		return boardDao.count();
	}

	//게시판10
	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {
		return boardDao.list(pv);
	}

	//게시판글쓰기저장7
	@Override
	public void insertProcess(BoardDTO dto) {
		//답변글이면
		if(dto.getRef() !=0) {
			boardDao.reStepCount(dto);
			dto.setRe_step(dto.getRe_step() +1);
			dto.setRe_level(dto.getRe_level() +1);
		}
		//제목글
		boardDao.save(dto);
	}

	//게시판 글 조회수 증가 후 글 조회 리턴
	@Override
	public BoardDTO contentProcess(int num) {
		boardDao.readCount(num);
		return boardDao.content(num);
	}

	//수정3
	@Override
	public BoardDTO updateSelectProcess(int num) {
		return boardDao.content(num);
	}

	//첨부파일수정3
	@Override
	public void updateProcess(BoardDTO dto, String urlpath) {
		String filename = dto.getUpload();
		
		//수정할 첨부파일이 있으면
		if(filename != null) {
			
			String path = boardDao.getFile(dto.getNum());
			//기존 첨부파일이 있으면
			if(path != null) {
				File file = new File(urlpath, path);
				file.delete();
			}
		}
		
		boardDao.update(dto);
	}

	//삭제3
	@Override
	public void deleteProcess(int num, String urlpath) {
		String path = boardDao.getFile(num);
		if(path!=null){//첨부파일이 있으면 먼저 삭제해야함
			File file = new File(urlpath, path);
			file.delete();
		}
		boardDao.delete(num);//첨부파일 삭제 후 삭제
	}

	@Override
	public String fileSelectprocess(int num) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
