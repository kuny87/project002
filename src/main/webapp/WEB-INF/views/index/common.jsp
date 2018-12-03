<%@page import="com.next.sample.model.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<fmt:formatDate value="${now}" var="nowDate" pattern="yyyy-MM-dd HH:mm:ss" />

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="resourcesPath" value="${contextPath}/resources"/>
  
<style>
	.center {text-align: center;}
	.right {text-align: right;}
	.left {text-align: left;}
	.cp {cursor: pointer; color: blue;}
	.active {font-size: 22px;}
</style>

<head>
	<div class="right" style="width: 78%">
		<c:if test="${user.id != null }">
			${user.id} 님
			<button onclick="javascript:logout()">로그아웃</button>
		</c:if>
		<c:if test="${user.id == null }">
			<button onclick="javascript:login()">로그인</button>
		</c:if>
		<button onclick="javascript:mypage()">마이페이지</button>
	</div>
	
	<div style="width: 45%; margin:auto; margin-top:20px; margin-bottom: 40px;">
		<button style="margin-right: 15px;" onclick="javascript:main()">메인</button>
		<button style="margin-right: 15px;" onclick="javascript:board_faq_list()">프로필</button>
		<button style="margin-right: 15px;" onclick="javascript:board_notice_list()">공지사항</button>
		<button style="margin-right: 15px;" onclick="javascript:board_normal_list()">자유게시판</button>
		<button onclick="javascript:parsing()">파싱</button>
	</div>
	
</head>

<script>

	var contextPath = '${contextPath}',
		resourcePath = '${resourcePath}';

	// 로그인
	function login() {
		location.href = contextPath + '/user_login';
	}
	
	// 로그아웃
	function logout() {
		location.href = contextPath + '/user_logout';
	}
	
	// 메인페이지
	function main() {
		location.href = contextPath + '/main';
	}
	
	// 마이페이지
	function mypage() {
		location.href = contextPath + '/user_mypage';
	}
	
	// 자유 게시판
	function board_normal_list() {
		location.href = contextPath + "/board_normal_list";
	}
	
	// 공지사항 게시판
	function board_notice_list() {
		location.href = contextPath + "/board_notice_list";
	}
	
	// FAQ 게시판
	function board_faq_list() {
		location.href = contextPath + "/board_faq_list";
	}
	
	// 파싱
	function parsing() {
		
		$.ajax({
			dataType : 'json',
			url: contextPath + "/rest/parsing",
			method : 'GET'
		}).done( function(result) {
			if (result.success) {
				alert("파싱 완료");
			}else {
				alert("파싱 오류 : " + result.message);
			}
		}).fail(function(result) {
			alert("파싱 실패")
		});
		
	}
	
</script>
