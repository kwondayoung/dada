package com.newlecture.webapp.controller;

import java.util.List;

import javax.activation.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.webapp.dao.NoticeDao;

import com.newlecture.webapp.entity.NoticeView;

// /customer/notice
// /customer/notice-detail?c=3
// /customer/notice/3
// www.cyworld.com/newlec

@Controller
@RequestMapping("/customer/*")
public class CustomerController {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@RequestMapping("notice")
	//@ResponseBody
	public String notice(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field,
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);  
		//List<NoticeView> list = noticeDao.getList(1, "title", "");
		List<NoticeView> list = noticeDao.getList(page, field, query);
		
/*		String output = String.format("p:%s, q:%s", page, query);
		output += String.format("title : %s", list.get(0).getTitle());*/
		
		model.addAttribute("list", list);
		
		return "customer/notice";
	}
	
	@ResponseBody	
	@RequestMapping("notice/{id}")	
	public String noticeDetail(
			@PathVariable("id") String aaid) {
		
		//NoticeDao noticeDao =  new SpringNoticeDao();
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);  

		
		NoticeView noticeView = noticeDao.get(aaid);
		
		
		return aaid+"번째 공지사항"+noticeView.getTitle();
	}
	
	
}
