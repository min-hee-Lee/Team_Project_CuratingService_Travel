package board.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.dto.BoardDTO;
import board.dto.PageDTO;
import board.service.BoardService;
import common.file.FileUpload;
import members.dto.AuthInfo;

@Controller
public class BoardController {

	//게시판4,11
	private BoardService boardService;
	private PageDTO pdto;
	
	public BoardController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView listExecute(@ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
		//System.out.println("pv:" + pv.getCurrentPage());
		int totalRecord = boardService.countProcess();
		if(totalRecord>=1) {
			if(pv.getCurrentPage()==0)
				pv.setCurrentPage(1);
			this.pdto = new PageDTO(pv.getCurrentPage(), totalRecord);
			mav.addObject("pv", this.pdto);
			mav.addObject("aList", boardService.listProcess(this.pdto));
		}
		mav.setViewName("board/list");
		return mav;
	}
	
	//게시판글쓰기2(답변글 포함 같은 형식)
	@RequestMapping(value="/board/write.do", method=RequestMethod.GET)
	public ModelAndView writeExecute(@ModelAttribute("dto") BoardDTO dto, @ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
		mav.setViewName("board/write");
		return mav;
	}
	
	//게시판글쓰기저장9
	@RequestMapping(value="/board/write.do", method=RequestMethod.POST)
	public String writeProExecute(BoardDTO dto, PageDTO pv, HttpServletRequest req, HttpSession session, RedirectAttributes ratt) {
		MultipartFile file = dto.getFilename();
		
		//System.out.println(dto.getMembersDTO().getMemberName());
		
		//파일 첨부가 있으면
		if(!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, req);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}
		
		dto.setIp(req.getRemoteAddr());
		
		//AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		//dto.setMemberEmail(authInfo.getMemberEmail());
		
		boardService.insertProcess(dto);
		
		if(dto.getRef()!=0) {
			ratt.addAttribute("currentPage", pv.getCurrentPage());//답변글일때만 현재 페이지값 리턴
		}
		
		return "redirect:/board/list.do"; //글쓰기가 끝나고 저장한 뒤 리스트(1페이지)가 보이게
	}
	
	//게시판 글 확인
	@RequestMapping("/board/view.do")
	public ModelAndView viewExecute(int currentPage, int num, ModelAndView mav) {
		//System.out.printf("currentPage:%d, num:%d\n", currentPage, num);
		mav.addObject("dto", boardService.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board/view");
		return mav;
	}
	
	//수정2
	@RequestMapping(value="/board/update.do", method=RequestMethod.GET)
	public ModelAndView updateExecute(int num, int currentPage, ModelAndView mav) {
		mav.addObject("dto", boardService.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board/update");
		return mav;
	}
	
	//첨부파일수정6
	@RequestMapping(value="/board/update.do", method=RequestMethod.POST)
	public String updateExecute(BoardDTO dto, int currentPage, HttpServletRequest request, RedirectAttributes ratt) {
		MultipartFile file = dto.getFilename();
		if(!file.isEmpty()) {
			UUID random = FileUpload.saveCopyFile(file, request);
			dto.setUpload(random + "_" + file.getOriginalFilename());
		}
		
		boardService.updateProcess(dto, FileUpload.urlPath(request));
		
		//return "redirect:/board/list.do?currentPage=" + currentPage;
		
		//primitive값만 가능
		ratt.addAttribute("currentPage", currentPage);//get방식으로 노출됨
		
		//Object값 가능 (session에 담아서 넘겨줌)
		//ratt.addFlashAttribute("dto", dto);  //get방식이지만 session에 담아서 넘겨주므로 노출이 안됨.
		
		//ratt.addFlashAttribute("currentPage", currentPage);
		return "redirect:/board/list.do";
	}
	
	//삭제4
	@RequestMapping("/board/delete.do")
	public String deleteExecute(int num, int currentPage, HttpServletRequest request, RedirectAttributes ratt){
		ratt.addAttribute("currentPage", currentPage);
		boardService.deleteProcess(num, FileUpload.urlPath(request));
		return "redirect:/board/list.do";
	}
	
	//첨부파일 다운로드1
	@RequestMapping("/board/contentdownload.do")
	public ModelAndView downloadExecute(int num, ModelAndView mav) {
		mav.addObject("num", num);
		mav.setViewName("download");
		return mav;
	}
	
}//end class























