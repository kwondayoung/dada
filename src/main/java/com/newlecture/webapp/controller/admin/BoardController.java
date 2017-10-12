package com.newlecture.webapp.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeFile;
import com.newlecture.webapp.entity.NoticeView;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {
	
	
	@Autowired
	private NoticeDao noticeDao; //IOC containter에 객체가 잉ㅅ어어엉
	
	@Autowired
	private NoticeFileDao noticeFileDao;
	
	@RequestMapping("notice")	
	public String notice(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field,
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		
		List<NoticeView> list = noticeDao.getList(page, field, query);
		model.addAttribute("list", list);
		
		return "admin.board.notice.list";
		
	}

	@RequestMapping("notice/{id}")	
	public String noticeDetail(
			@PathVariable("id") String id, Model model) {

		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
		
		
		return "admin.board.notice.detail";
	}
	
	
	@RequestMapping(value="notice/reg", method=RequestMethod.GET)	
	public String noticeReg() {

		return "admin.board.notice.reg"; //forward 방식
		
	}
	
	
	
/*	@RequestMapping(value="notice/reg", method=RequestMethod.POST)	
	public String noticeReg(
			String title, 
			String content) throws UnsupportedEncodingException {
		
		
		//title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); //한글 받아오기 --> 필터로 옮기겟다
		//System.out.println(title);

		String writerId = "newlec";
		
		int row = noticeDao.insert(title, content, writerId);
		//int row2 = noticeDao.insert(new Notice(title, content, writerId));
		
		
		
		return "redirect:../notice";
		
	}*/
	
	// 위에 방식이 아닌 Notice 객체로 받기 
	@RequestMapping(value="notice/reg", method=RequestMethod.POST)	
	public String noticeReg(
			Notice notice, 
			String aa, 
			MultipartFile file,
			HttpServletRequest request) throws IOException {
		
		//날짜 얻는 방법1
		//Date curDate = new Date();
		//curDate.getYear();
		
		//날짜 얻는 방법2 년도수까지
		Calendar cal = Calendar.getInstance();
		//Date curDate2 = cal.getTime();
		int year = cal.get(Calendar.YEAR);
		
		//날짜 얻는 방법3 문자열로 바꾸고싶을때
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//SimpleDateFormat fmt = new SimpleDateFormat("hh:mm:ss");
		//String year= fmt.format(curDate);
		
		String nextId = noticeDao.getNextId();
		
		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath("/resource/customer/notice/"+year+"/"+nextId);
		//String path = ctx.getRealPath(String.format("/resource/customer/notice/%d/%s",year,nextId);
		
		System.out.println(path);
		
		File f = new File(path); //경로
		if(!f.exists()) {
			if(!f.mkdirs())
				System.out.println("디렉토리를 생성할 수 없습니다.");
		}
		
		// d:\a\b\c\d/aa.jpg
		path += File.separator+file.getOriginalFilename();
		File f2 = new File(path);
		
		//스트림: 데이터가 들어있는 버퍼 //입력쪽으로, 출력쪽으로의 방향성이 있다. //따라서 스트림 버퍼,스트림이다.
		//
		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);
		
		byte[] buf = new byte[1024];
		
		int size = 0;
		while((size = fis.read(buf))>0)
			fos.write(buf, 0, size);
		
		fis.close();
		fos.close();
		
		//file.getInputStream();
		String fileName= file.getOriginalFilename();
		System.out.println(fileName);
		
		String writerId = "newlec";
		System.out.println(notice.getTitle());
		
		notice.setWriterId(writerId);
		int row = noticeDao.insert(notice);
		
		noticeFileDao.insert(new NoticeFile(null, fileName, nextId));
		//int row2 = noticeDao.insert(new Notice(title, content, writerId));
		
		
		
		return "redirect:../notice";
		
	}
	
	
}
