<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<jsp:include page="/WEB-INF/views/index/common.jsp" flush="false" />
	<title>글쓰기</title>
</head>
<style>
	table, th, td {
		border: 1px solid black;
	}
	
	table {
		width:60%;
		height:200px;
		margin:auto;
		text-align: center;
	}
</style>
<body>

<div>
	<h1 class="center">글쓰기</h1>
</div>

<div>
	<table>
		<tbody>
			<tr>
				<td>제목</td>
				<td><input type="text" id="title" name="title"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" id="content" name="content"/></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><span>${user.name}</span></td>
			</tr>
		</tbody>
	</table>
	
	<div class="center" style="margin-top: 50px;">
		<button onclick="javascript:regist()">작성</button>
		<button onclick="javascript:list()">목록</button>
	</div>
</div>

<script>

	// 글등록
	function regist() {
		
		var $title = $('#title');
		var $content = $('#content');
		var name = '${user.name}';
		var regSeq = '${user.userSeq}';
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
			url: contextPath + "/rest/board_regist",
			method : 'GET',
			data : {
			   "type" : "NORMAL",
			   "title" : $title.val(),
			   "content" : $content.val(),
			   "name" : name,
			   "regSeq" : regSeq
			}			
		}).done( function(result) {
			if (result.success) {
				alert("등록완료");
				location.href = contextPath + "/board_normal_list";
			}else {
				alert(result.message);
			}
		}).fail(function(result) {
			alert("등록실패");
		});
		
	}
	
	// 글목록
	function list() {
		location.href = contextPath + "/board_normal_list";
	}
	
</script>

</body>
</html>
