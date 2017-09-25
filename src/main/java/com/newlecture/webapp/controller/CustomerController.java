package com.newlecture.webapp.controller;

import javax.activation.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.spring.SpringNoticeDao;
import com.newlecture.webapp.entity.NoticeView;

// /customer/notice
// /customer/notice-detail?c=3
// /customer/notice/3
// www.cyworld.com/newlec

@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	

	
	@RequestMapping("notice")
	@ResponseBody	
	public String notice(
			@RequestParam(value="p", defaultValue="1") Integer p,
			@RequestParam(value="q", defaultValue="") String q) {
		
		String output = String.format("p:%s, q:%s", p, q);
		
		return output;
	}
	
	@ResponseBody	
	@RequestMapping("notice/{id}")	
	public String noticeDetail(
			@PathVariable("id") String aaid) {
		
		NoticeDao noticeDao =  new SpringNoticeDao();
		NoticeView noticeView = noticeDao.get(aaid);
		
		
		return aaid+"번째 공지사항"+noticeView.getTitle();
	}
	
	
}
