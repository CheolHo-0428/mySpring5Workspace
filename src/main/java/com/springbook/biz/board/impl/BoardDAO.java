package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		private final String BOARD_INSERT = 
				"insert into BOARD(title, writer, content) values(?,?,?)";
		private final String BOARD_UPDATE = 
				"update BOARD set title=?, writer=?, content=? where seq=?";
		private final String BOARD_DELETE = 
				"delete from BOARD where seq=?";
		private final String BOARD_GET = "select * from BOARD where seq= ?";
		private final String BOARD_LIST = "select * from BOARD order by seq desc";
		private final String BOARD_LIST_T = "select * from BOARD where title like concat('%',?,'%') order by seq desc";
		private final String BOARD_LIST_C = "select * from BOARD where content like concat('%',?,'%') order by seq desc";
		
		//글등록
		public void insertBoard(BoardVO vo) {
			System.out.println("===> JDBC로 insertBoard() 기능 처리");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_INSERT);
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getWriter());
				stmt.setString(3, vo.getContent());
				stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}	
		}
		
		//글수정
		public void updateBoard(BoardVO vo) {
			System.out.println("===> JDBC로 updateBoard() 기능 처리");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_UPDATE);
				stmt.setString(1, vo.getTitle());
				stmt.setString(2, vo.getWriter());
				stmt.setString(3, vo.getContent());
				stmt.setInt(4, vo.getSeq());
				stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		//글삭제
		public void deleteBoard(BoardVO vo) {
			System.out.println("===> JDBC로 deleteBoard() 기능 처리");
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_DELETE);
				stmt.setInt(1, vo.getSeq());
				stmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}
		}
		
		//글상세 조회
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===> JDBC로 getBoard() 기능 처리");
			BoardVO board = null;
			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(BOARD_GET);
				stmt.setInt(1, vo.getSeq());
				rs = stmt.executeQuery();
				if(rs.next()) {
					board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return board;
		}
		
		//글목록 조회
		public List<BoardVO> getBoardList(BoardVO vo){
			System.out.println("===> JDBC로 getBoardList() 기능 처리");
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			try {
				conn = JDBCUtil.getConnection();
				if(vo.getSearchCondition().equals("TITLE")) {
					stmt = conn.prepareStatement(BOARD_LIST_T);
				} else if(vo.getSearchCondition().equals("CONTENT")) {
					stmt = conn.prepareStatement(BOARD_LIST_C);
				}
				stmt.setString(1, vo.getSearchKeyword());
				//stmt = conn.prepareStatement(BOARD_LIST);
				rs = stmt.executeQuery();
				while(rs.next()) {
					BoardVO board = new BoardVO();
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
					boardList.add(board);
					}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, stmt, conn);
			}
			return boardList;
		} 
}
