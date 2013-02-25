<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />


		<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/search.js"></script>


		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ja"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />



	<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
		<title>メインページ</title>
	</head>
<body>
	<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="followpage" />
	<tiles:put name="content" type="string">

	<p>${mydata.username }は${mydata.follow }人をフォローしています</p>

	<c:if test="${empty followList}">
		<div>
			まだだれもフォローしていません
		</div>
	</c:if>

	<c:if test="${!empty followList}">
	<div id ="tuserMain">
	<c:forEach var="followList" items="${followList}">

		<s:link href="/main/showdata/${followList.usernick}" style="text-decoration: none"> ${followList.usernick} </s:link>
		<p>${followList.newMur}</p>
		<p><fmt:formatDate value="${followList.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></p>

		<c:if test="${mydata.userid==mine}">
		<s:link onclick="return confirm('delete OK?');" href="unfollow/${followList.userid}">followを解除</s:link>
		</c:if>


	</c:forEach>

	</div>
	</c:if>
	</tiles:put>
	</tiles:insert>
</body>
</html>
