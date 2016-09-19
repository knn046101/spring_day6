package com.example.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.comment.config.CommentConfig;
import com.example.comment.dto.Comment;
import com.example.comment.repo.CommentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={CommentConfig.class})
public class CommentTest {

	private static Logger logger = LoggerFactory.getLogger(CommentTest.class);
	
	@Autowired
	CommentRepo repo;
	@Autowired
	DataSource ds;
	@Autowired
	SqlSessionTemplate template;

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void ComponentTest() throws SQLException {
		assertThat(repo, is(notNullValue()));
		Connection con = ds.getConnection();
		assertThat(con, is(notNullValue()));
	}

	@Test
	public void templateTest()  {
		assertThat(template, is(notNullValue()));
	}
	
	@Test
	public void insertTest()  {
		Comment comment = new Comment();
		comment.setCommentNo(0);
		comment.setUserId("hong");
		comment.setContent("아가리또");
		int result=repo.insert(comment);
		logger.trace("comment no : {}", comment.getCommentNo());
		assertThat(result, is(1));
	}
}
