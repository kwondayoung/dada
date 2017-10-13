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

import com.google.gson.Gson;
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
	private NoticeDao noticeDao; //이 framework에서 는 어떤 lib를 썻는 지 흔적을 남기지 않ㄴ는다.

	@RequestMapping("notice-ajax")
	@ResponseBody
	public String noticeA(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field,
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		//NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);  
		//List<NoticeView> list = noticeDao.getList(1, "title", "");
		//List<NoticeView> list = noticeDao.getList(page, field, query);
		
/*		String output = String.format("p:%s, q:%s", page, query);
		output += String.format("title : %s", list.get(0).getTitle());*/
		
		//model.addAttribute("list", noticeDao.getList(page, field, query));
		
		List<NoticeView> list = noticeDao.getList(page, field, query);
		
		String json = "";
		
		Gson gson = new Gson();
		json = gson.toJson(list);
		
/*		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("{\"id\":\""+list.get(0).getID()+"\",\"title"\");
		
		builder.append("{}");
		builder.append("]");
		
		json = builder.toString();*/ //gson이 해줌
		
		
		return json;
	}
	
	@RequestMapping("notice")
	//@ResponseBody
	public String notice(
			@RequestParam(value="p", defaultValue="1") Integer page,
			@RequestParam(value="f", defaultValue="title") String field,
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		//NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);  
		//List<NoticeView> list = noticeDao.getList(1, "title", "");
		//List<NoticeView> list = noticeDao.getList(page, field, query);
		
/*		String output = String.format("p:%s, q:%s", page, query);
		output += String.format("title : %s", list.get(0).getTitle());*/
		
		//model.addAttribute("list", noticeDao.getList(page, field, query));
		
		List<NoticeView> list = noticeDao.getList(page, field, query);
		model.addAttribute("list", list);
		
		return "customer.notice.list";
	}
	
	//@ResponseBody	
	@RequestMapping("notice/{id}")	
	public String noticeDetail(
			@PathVariable("id") String id, Model model) {
		
		//NoticeDao noticeDao =  new SpringNoticeDao();
		
		//NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);  

		
		//NoticeView noticeView = noticeDao.get(aaid);
		
		
		//return aaid+"번째 공지사항"+noticeView.getTitle();
		
		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
		
		
		return "customer.notice.detail";
	}
	
	
}
