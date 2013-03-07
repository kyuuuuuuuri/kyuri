<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/follow.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />



		<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>

		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ja"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />

	<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
		<title>recommendAll</title>
	</head>
<body>
	<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="followpage" />
	<tiles:put name="content" type="string">


	<c:if test="${empty recommendMap}">
		<div id="noFollow">
			mokyu
		</div>
	</c:if>

	<c:if test="${!empty recommendMap}">
	<div id ="tuserMain">

	<c:forEach var="recommend" items="${ recommendMap }">

	<div id="${ recommend.value.userid }" class="twitmain">
		<span class="pImg">
			<html:img src="${pageContext.request.contextPath}/main/showUserImg/${ recommend.value.userid }"
					width="50" height="50" />
		</span>
			<s:link href="userpage/${ recommend.value.usernick }"> ${ recommend.value.usernick } </s:link>

		<div class="followUserList">
		<c:forEach var="user" items="${ recommend.value.followUserList }">
		<span class="followUserList">
			${user},
		</span>
		</c:forEach>
		<span> がフォローしています</span>

		</div>
	</div>
		<p class="timeLine_border"></p>

	</c:forEach>

	</div>
	</c:if>
	<div id="lastLine" class="0">　</div>

	</tiles:put>
	</tiles:insert>
</body>
</html>
