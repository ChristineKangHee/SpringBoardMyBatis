package com.example.dao;

import com.example.bean.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Autowired
	SqlSession sqlSession;

	private JdbcTemplate template;
	public void setTemplate(JdbcTemplate template){
		this.template=template;
	}

	private final String BOARD_INSERT = "insert into BOARD4 (title, writer, content) values (?,?,?)";
	private final String BOARD_UPDATE = "update BOARD4 set title=?, writer=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD4  where seq=?";
	private final String BOARD_GET = "select * from BOARD4  where seq=?";
	private final String BOARD_LIST = "select * from BOARD4 order by seq desc";

	public int insertBoard(BoardVO vo) {
		int result = sqlSession.insert("Board.insertBoard", vo);
		return result;
	}

	// 글 삭제
	public int deleteBoard(int id) {
		int result = sqlSession.delete("Board.deleteBoard", id);
		return result;
	}
	public int updateBoard(BoardVO vo) {
		int result = sqlSession.update("Board.updateBoard", vo);
		return result;
	}	
	
	public BoardVO getBoard(int seq) {
		BoardVO one=sqlSession.selectOne("Board.getBoard", seq);
		return one;
	}
	
	public List<BoardVO> getBoardList(){
		List<BoardVO> list=sqlSession.selectList("Board.getBoardList");
		return list;
	}
}
