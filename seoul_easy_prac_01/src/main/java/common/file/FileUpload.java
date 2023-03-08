package common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

//게시판글쓰기저장10
public class FileUpload {

	//첨부파일 저장 시 파일명 중복제거를 위해 파일명 앞에 난수값 발생시킴
	public static UUID saveCopyFile(MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		
		//UUID : 16진수 난수값 발생
		UUID random = UUID.randomUUID();
		
		File ff = new File(urlPath(request), random + "_" + fileName);
							//urlPath(request)이 위치에 난수값에 언더바를 더한 파일명으로 저장해라.
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return random;
	}//end saveCopyFile
	
	public static String urlPath(HttpServletRequest request) {//urlpath를 통해 첨부파일 위치 받아옴
		String root = request.getSession().getServletContext().getRealPath("/");//첨부파일 저장할 위치 받아오기
		System.out.print("root" + root);
		String saveDirectory = root + "temp" + File.separator; //temp라는 폴더를 생성해 저장
		
		File fe = new File(saveDirectory);
		if(!fe.exists())
			fe.mkdir();
		
		return saveDirectory;
		//urlPath : 첨부파일을 저장할 위치를 계산함
	}
	
}//end class






















