<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<jsp:include page="/WEB-INF/views/index/common.jsp" flush="false" />
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<title>자유게시판</title>
</div>
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
	<h1 class="center">자유게시판</h1>
</div>

<div>
	<input type="hidden" id="pageNo" name="pageNo" value="${paging.pageNo}"/>
	<div class="center" style="margin-bottom: 20px;">
		
		<select id="boardSize" name="boardSize">
			<c:forEach var="list" items="${boardSizeTypeList}">
				<c:choose>
					<c:when test="${boardSize eq list.getSValue()}">
						<option value="${list.getSValue()}" selected>${list.getVName()}</option>
					</c:when>
					<c:otherwise>
						<option value="${list.getSValue()}">${list.getVName()}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>

		<fmt:parseDate value="${startDate}" var="sDate" pattern="yyyy-MM-dd" />
		<fmt:parseDate value="${endDate}" var="eDate" pattern="yyyy-MM-dd" />
		<fmt:formatDate value="${sDate}" var="sDateStr" pattern="yyyy-MM-dd" />
		<fmt:formatDate value="${eDate}" var="eDateStr" pattern="yyyy-MM-dd" />
		
		<label for="startDate">from</label>
		<input type="text" class="datepicker" id="startDate" name="startDate" value="${sDateStr}" >
		<label for="endDate">to</label>
		<input type="text" class="datepicker" id="endDate" name="endDate" value="${eDateStr}" >
		
		<select id="searchType" name="searchType">
			<c:forEach var="list" items="${searchTypeList}">
				<c:choose>
					<c:when test="${searchType eq list.getName()}">
						<option value="${list.getName()}" selected>${list.getVName()}</option>
					</c:when>
					<c:otherwise>
						<option value="${list.getName()}">${list.getVName()}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<input type="text" id="keyword" name="keyword" value="${keyword}" onkeydown="onEnter();"/> 
		<button onclick="search()">검색</button>
	</div>
	
	<div>
		<table>
			<colgroup>
				<col width="10%" />
				<col width="35%" />
				<col width="15%" />
				<col width="10%" />
				<col width="30%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일자</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${paging.totalBoard eq 0}">
					<tr>
						<td colspan="5" class="center">검색결과가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="board" items="${paging.list}" varStatus="status">
					<tr>
						<td>${paging.totalBoard - (paging.pageNo-1)*paging.boardSize - status.index }</td>
						<td><a href="javascript:get(${board.boardSeq})">${board.title } 
						<c:if test="${board.replyCount ne null}">
						[${board.replyCount }]
						</c:if>
						</a></td>
						<td>${board.name }</td>
						<td>${board.cnt }</td>
						<fmt:parseDate value="${board.regDate}" var="regDate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${regDate}" var="rDate" pattern="yyyy-MM-dd HH:mm:ss" />
						<td>${rDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="center" style="margin-top: 20px;">
		<c:if test="${paging.startPage - paging.pageSize gt 0}">
			<span class="paginate_button previous disabled" id="DataTables_Table_0_previous">
				<a href="javascript:pageModule.movePage(${paging.startPage-1})" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0">Previous</a>
			</span>
		</c:if>
		<c:forEach varStatus="i" begin="${paging.startPage}" end="${paging.endPage}" step="1">
			<span class="paginate_button <c:if test="${paging.pageNo eq i.index}">active</c:if>">
				<a href="javascript:pageModule.movePage(${i.index})" aria-controls="DataTables_Table_0" tabindex="0">${i.index}</a>
			</span>
		</c:forEach>
		<c:if test="${paging.totalPage - paging.endPage gt 0}">
			<span class="paginate_button next" id="DataTables_Table_0_next">
				<a href="javascript:pageModule.movePage(${paging.startPage+paging.pageSize})" aria-controls="DataTables_Table_0" data-dt-idx="7" tabindex="0">Next</a>
			</span>
		</c:if>
	</div>
		
	<div class="center" style="margin-top: 30px;">
		<button onclick="regist()">글쓰기</button>
	</div>
</div>

<script>

	var boardSize = $('#boardSize').val();
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var searchType = $('#searchType').val();
	var keyword = $('#keyword').val();
	var pageNo = '${paging.pageNo}';

	//페이지 버튼 클릭 시
	var pageModule = (function() {
		function movePage(param) {
			if(startDate != '' && endDate != ''){
				location.href = location.pathname + "?startDate=" + startDate + "&endDate=" + endDate + "&searchType=" + param.searchType + "&keyword=" + param.keyword + "&boardSize=" + param.boardSize + "&pageNo=" + param.pageNo;				
			}else {
				location.href = location.pathname + "?searchType=" + param.searchType + "&keyword=" + param.keyword + "&boardSize=" + param.boardSize + "&pageNo=" + param.pageNo;
			}
		}
	
		return {
			movePage : function(pageNo) {
				// 페이지 번호, 검색 시작날짜, 검색 끝 날짜, 검색유형, 검색어를 JSON형태로 저장해서 파라미터로 보낸다. (여기서  searchType은 컨트롤러에서 가져온 사용자가 선택한 검색 유형)
				var param = {
						pageNo : pageNo, 
						searchType : searchType, 
						keyword : keyword,
						boardSize : boardSize
					};
				movePage(param);
			},
			
			searchPage : function(param) {
				movePage(param);
			}
		}
	})();

	
	// 글쓰기
	function regist() {
		location.href = contextPath + '/board_normal_regist';
	}
	
	// 글확인
	function get(boardSeq) {
		if(startDate != '' && endDate != ''){
			location.href = contextPath + '/board_normal_get?startDate=' + startDate + '&endDate=' + endDate + '&searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&boardSeq=' + boardSeq + '&pageNo=' + pageNo;
		}else {
			location.href = contextPath + '/board_normal_get?searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&boardSeq=' + boardSeq + '&pageNo=' + pageNo;	
		}
	}
	
	
	// 엔터키로 검색버튼 클릭
	function onEnter(){
		var keyCode = window.event.keyCode;
		if(keyCode == 13) {
			search();
		}
	}
	
	
	// 검색
	function search() {
		var boardSize = $('#boardSize').val();
		var searchType = $('#searchType').val();
		var keyword = $('#keyword').val();
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		
		if(startDate != '' && endDate != ''){
			location.href = contextPath + '/board_normal_list?startDate=' + startDate + '&endDate=' + endDate + '&searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&pageNo=1';
		}else {
			location.href = contextPath + '/board_normal_list?searchType=' + searchType + '&keyword=' + keyword + '&boardSize=' + boardSize + '&pageNo=1';	
		}
	}
	
	// jquery ui 달력
	$( function() {
		var dateFormat = "yy-mm-dd",
		    startDate = $( "#startDate" )
		      .datepicker({
		        defaultDate: "+1w",
		        changeMonth: true,
		        numberOfMonths: 3
		      })
		      .on( "change", function() {
		    	 endDate.datepicker( "option", "minDate", getDate(this) );
		      }),
		    endDate = $( "#endDate" ).datepicker({
		      defaultDate: "+1w",
		      changeMonth: true,
		      numberOfMonths: 3
		    })
		    .on( "change", function() {
		      startDate.datepicker( "option", "maxDate", getDate(this) );
		    });
		
		function getDate( element ) {
		    var date;
		    try {
		      date = $.datepicker.parseDate( dateFormat, element.value );
		    } catch( error ) {
		      date = null;
		    }
		
		 	// Setter
		    $( ".datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		    
		    return date;
		}
		
	});
</script>

</body>
</html>
