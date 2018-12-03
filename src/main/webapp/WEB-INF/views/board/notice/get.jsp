<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<jsp:include page="/WEB-INF/views/index/common.jsp" flush="false" />	
	<title>공지사항</title>
	
	<style>
		table.content, th, td {
			border: 1px solid black;
		}
		
		table.content {
			width:60%;
			height:200px;
			margin:auto;
			text-align: center;
		}
		
		table.table_reply_regist {
			width:100%;
			margin:auto;
			text-align: center;
		}
		
		.hide {
			display: none;
		}
	</style>
</head>

<body>

<div>
	<h1 class="center">공지사항</h1>
</div>

<div>
	
	<input type="hidden" id="boardSeq" name="boardSeq" value="${board.boardSeq}" />
	
	<div>
		<table class="content">
			<colgroup>
				<col width="15%" />
				<col width="65%" />
				<col width="20%" />
			</colgroup>
			<tbody>
				<tr>
					<td>제목</td>
					<td>
						<c:if test="${user.id == 'admin'}">
							<input type="text" id="title" name="title" style="width: 100%" value="${board.title}" />
						</c:if>
						<c:if test="${user.id != 'admin'}">
							<span>${board.title}</span>
						</c:if>
					</td>
					<td>조회수 : ${board.cnt}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="2">
						<c:if test="${user.id == 'admin'}">
							<textarea id="content" rows="10" cols="50" maxlength="6000" style="overflow:hidden; line-height: 14px; height: 60px; width: 100%;" title="내용">${board.content}</textarea>
						</c:if>
						<c:if test="${user.id != 'admin'}">
							<span>${board.content}</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td colspan="2">${board.name}</td>
				</tr>
				<tr>
					<fmt:parseDate value="${board.regDate}" var="regDate" pattern="yyyy-MM-dd HH:mm:ss" />
					<fmt:formatDate value="${regDate}" var="rDate" pattern="yyyy-MM-dd HH:mm:ss" />
					<td>작성일</td>
					<td colspan="2">${rDate}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="center" style="margin-top: 50px;">
		<c:if test="${user.id == 'admin'}">
			<button onclick="modify()">수정</button>
			<button onclick="remove()">삭제</button>
		</c:if>
		<button onclick="list()">목록</button>
	</div>
	
</div>

<script>
	
	var boardSize = '${boardSearch.boardSize}';
	var startDate = '${boardSearch.startDate}'.substring(0,10);
	var endDate = '${boardSearch.endDate}'.substring(0,10);
	var searchType = '${boardSearch.searchType}';
	var keyword = '${boardSearch.keyword}';
	var pageNo = '${boardSearch.pageNo}';
	
	// 글목록
	function list() {
		if(startDate != '' && endDate != ''){
			location.href = contextPath + '/board_notice_list?startDate=' + startDate + '&endDate=' + endDate + '&searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&pageNo=' + pageNo;
		}else {
			location.href = contextPath + '/board_notice_list?searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&pageNo=' + pageNo;	
		}
	}
	
	// 글수정
	function modify() {

		var $boardSeq = $('#boardSeq');
		var $title = $('#title');
		var $content = $('#content');
		var validateMessage = null;
		var validateFocus = null;
		
		// input 데이터 체크 및 팝업text 입력, 포커스 입력
		if ($title.val() == "") {
			validateMessage = '제목을 입력해 주세요.';
			validateFocus = title;
		} else if ($content.val() == "") {
			validateMessage = '내용을 입력해 주세요.';
			validateFocus = content;
		}
		
		// input 데이터 체크 및 팝업창 띄워주고 포커스
		if(validateMessage != null) {
			validateFocus.focus();
			alert(validateMessage);
			return false;
		}
		
		$.ajax({
			dataType : 'json',
			url: contextPath + "/rest/board_modify",
			method : 'GET',
			data : {
			   "boardSeq" : $boardSeq.val(),
			   "title" : $title.val(),
			   "content" : $content.val()
			}			
		}).done( function(result) {
			if (result.success) {
				alert("수정완료")
			} else {
				alert(result.message);
			}
		}).fail(function(result) {
			alert("수정실패")
		});
			
	}
	
	// 글삭제
	function remove() {

		var remove_confirm = confirm("삭제하시겠습니까?");
		if(remove_confirm == true) {
			
			var boardSeq = $('#boardSeq').val();
			
			$.ajax({
				dataType : 'json',
				url: contextPath + "/rest/board_remove",
				method : 'GET',
				data : {
				   "boardSeq" : boardSeq
				}			
			}).done( function(result) {
				if (result.success) {
					alert("삭제완료")
					location.href = contextPath + "/board_notice_list";
				}
			}).fail(function(result) {
				alert("삭제실패")
			});
			
		}
		
	}
	
</script>
</body>
</html>
