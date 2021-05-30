package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		AbstractApplicationContext container 
			= new GenericXmlApplicationContext("applicationContext.xml"); 
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		BoardVO vo = new BoardVO();
		vo.setSeq(100);
		vo.setTitle("스웩");
		vo.setWriter("홍길동");
		vo.setContent("너와나의연결고리");
		boardService.insertBoard(vo);
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO list : boardList) {
			System.out.println(list.toString());
		}
		
		container.close();
	}
}
