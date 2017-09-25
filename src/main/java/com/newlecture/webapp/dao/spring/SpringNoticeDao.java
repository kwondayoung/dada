package com.newlecture.webapp.dao.spring;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

public class SpringNoticeDao implements NoticeDao {
	

	
	//@Autowired //null..? ioc container 에 있는거 사용가능 but 무조건 null
	private DataSource dataSource; //사용자 url과 계정정보를 담아두는

	public void setDataSource(DataSource dataSource) {
		this.dataSource =dataSource;
	}
	
	
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
		
		String sql = "SELECT * FROM NoticeView WHERE id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		
/*		JdbcTemplate.queryForObject(sql, requireType);		
		return null;*/
		
		return jdbcTemplate.queryForObject(sql,
				new Object[] {}, NoticeView.class);  // 코드가 매우 간단해짐
	}

	@Override
	public int update(String id, String title, String content) {
		return 0;
	}

}
