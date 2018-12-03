<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<jsp:include page="/WEB-INF/views/index/common.jsp" flush="false" />	
	<title>mypage</title>
	
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
</head>

<body>

<div>
	<h1 class="center">마이페이지</h1>
</div>

<div>
	
	<input type="hidden" id="userSeq" name="userSeq" value="${user.userSeq}" />
	
	<div>
		<table class="userInfo">
			<colgroup>
				<col width="35%" />
				<col width="65%" />
			</colgroup>
			<tbody>
				<tr>
					<td>아이디</td>
					<td>
						<span>${user.id}</span>
					</td>
				</tr>
				<tr>
					<td>현재 비밀번호</td>
					<td>
						<input type="password" id="currentPw" name="currentPw" />
					</td>
				</tr>
				<tr>
					<td>새 비밀번호</td>
					<td>
						<input type="password" id="newPw" name="newPw" />
					</td>
				</tr>
				<tr>
					<td>새 비밀번호 확인</td>
					<td>
						<input type="password" id="newPwConfirm" name="newPwConfirm" />
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" id="userName" name="userName" value="${user.name}" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="center" style="margin-top: 50px;">
		<button onclick="modify()">수정</button>
		<button onclick="remove()">탈퇴</button>
	</div>
	
</div>

<script>
	
	function remove() {

		var userSeq = $('#userSeq').val();
		var id = '${user.id}';
		var $currentPw = $('#currentPw');
		var validateMessage = null;
		var validateFocus = null;
		
		// input 데이터 체크 및 팝업text 입력, 포커스 입력
		if ($currentPw.val() == "") {
			validateMessage = '현재 비밀번호를 입력해 주세요.';
			validateFocus = currentPw;
		}
		
		// input 데이터 체크 및 팝업창 띄워주고 포커스
		if(validateMessage != null) {
			validateFocus.focus();
			alert(validateMessage);
			return false;
		}
		
		// 현재 비밀번호 체크
		$.ajax({
			dataType : 'json',
			url: contextPath + "/rest/user_pwCheck",
			method : 'GET',
			data : {
			   "id" : id,
			   "pw" : $currentPw.val()
			}			
		}).done( function(result) {
			
			if (result.success) {
				
				var remove_confirm = confirm("탈퇴하시겠습니까?");
				if(remove_confirm == true) {
					
					// 회원탈퇴
					$.ajax({
						dataType : 'json',
						url: contextPath + "/rest/user_remove",
						method : 'GET',
						data : {
						   "userSeq" : userSeq
						}			
					}).done( function(result) {
						if (result.success) {
							alert("탈퇴완료")
							location.href = contextPath + "/";
						}
					}).fail(function(result) {
						alert("탈퇴실패")
					});
				}
				
			} else {
				alert(result.message);
				$('#currentPw').focus();
			}
			
		}).fail(function(result) {
			alert("탈퇴실패")
		});
		
	}
	
	
	function modify() {
		
		var id = '${user.id}';
		var $currentPw = $('#currentPw');
		var $newPw = $('#newPw');
		var $newPwConfirm = $('#newPwConfirm');
		var $userName= $('#userName');
		var validateMessage = null;
		var validateFocus = null;
		
		// input 데이터 체크 및 팝업text 입력, 포커스 입력
		if ($currentPw.val() == "") {
			validateMessage = '현재 비밀번호를 입력해 주세요.';
			validateFocus = currentPw;
		} else if ($newPw.val() == "") {
			validateMessage = '새 비밀번호를 입력해 주세요.';
			validateFocus = newPw;
		} else if ($newPw.val() != $newPwConfirm.val()) {
			validateMessage = '비밀번호가 일치하지 않습니다.';
			validateFocus = newPwConfirm;
		} else if ($userName.val() == "") {
			validateMessage = '이름을 입력해 주세요.';
			validateFocus = userName;
		}
		
		// input 데이터 체크 및 팝업창 띄워주고 포커스
		if(validateMessage != null) {
			validateFocus.focus();
			alert(validateMessage);
			return false;
		}
		
		// 현재 비밀번호 체크
		$.ajax({
			dataType : 'json',
			url: contextPath + "/rest/user_pwCheck",
			method : 'GET',
			data : {
			   "id" : id,
			   "pw" : $currentPw.val()
			}			
		}).done( function(result) {
			
			if (result.success) {
				
				// 회원정보 수정
				$.ajax({
					dataType : 'json',
					url: contextPath + "/rest/user_modify",
					method : 'GET',
					data : {
					   "id" : id,
					   "pw" : $newPw.val(),
					   "name" : $userName.val()
					}			
				}).done( function(result) {
					if (result.success) {
						alert("수정완료");
						location.reload();
					}
				}).fail(function(result) {
					alert("수정실패");
				});
				
			} else {
				alert(result.message);
				$('#currentPw').focus();
			}
			
		}).fail(function(result) {
			alert("수정실패")
		});
		
	}

</script>
</body>
</html>
