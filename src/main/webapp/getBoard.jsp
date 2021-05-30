<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
//	request.setCharacterEncoding("utf-8");
//	String seq = request.getParameter("seq");
//	int num = Integer.parseInt(seq);
	
//	BoardVO vo = new BoardVO();
//	vo.setSeq(num);
//	BoardDAO boardDAO = new BoardDAO();
	
//	BoardVO Board =  boardDAO.getBoard(vo);
	
	BoardVO Board = (BoardVO) session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
</head>
<body>
	<center>
		<h1>글 상세</h1>
		<h3><a href="logout_proc.jsp">Log-out</a></h3>
		<hr>
		<form action="updateBoard.do" method="post">
			<input type="hidden" name="seq" value="<%=Board.getSeq()%>">
			<table border="1" cellpadding=0 cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">제목</th>
					<td align="left">
						<input type="text" name="title" value="<%=Board.getTitle()%>">
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td align="left">
						<input type="text" name="writer" value="<%=Board.getWriter()%>">
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td align="left">
						<textarea rows="10" cols="40" name="content">
							<%=Board.getContent()%>
						</textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">등록일</td>
					<td align="left"><%=Board.getRegdate() %></td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td align="left"><%=Board.getCnt()%></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 수정">
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="insertBoard.jsp">글등록</a>&nbsp; &nbsp; &nbsp;
		<a href="deleteBoard.do?seq=<%=Board.getSeq()%>">글삭제</a>&nbsp; &nbsp; &nbsp;
		<a href="getBoardList.do">글목록</a>
	</center>
	
</body>
</html>