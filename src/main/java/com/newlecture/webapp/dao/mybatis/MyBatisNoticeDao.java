package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

public class MyBatisNoticeDao implements NoticeDao {

	@Override
	public List<NoticeView> getList(int page, String query) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public NoticeView get(String id) {
		return null;
	}

	@Override
	public int update(String id, String title, String content) {
		return 0;
	}

}
