package com.newlecture.webapp.dao;

import java.util.List;

import com.newlecture.webapp.entity.NoticeFile;

public interface NoticeFileDao {
	
	List<NoticeFile> getListByNoticeId(String noticeId);
	
	int insert(NoticeFile noticefile); 
	int update(NoticeFile noticefile); 
	int delete(String id); 
}
