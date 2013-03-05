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

	<c:if test="${empty favoliteList}">
		<span>誰もお気に入りしていません</span>
	</c:if>

	<c:if test="${!empty favoliteList}">
	<div id ="tuserMain">
	<c:forEach var="favoList" items="${favoliteList}">
		<div class="twitmain">
		<span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${favoList.tuser.userid}"
							width="50" height="50" />
					</span>
			<s:link href="userpage/${favoList.tuser.usernick}"> ${favoList.tuser.usernick} </s:link>
			<p>${favoList.tuser.newMur}</p>
			<p class="newTwitTime"><fmt:formatDate value="${favoList.tuser.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></p>

			<div class="followButton">
				<c:if test="${favoList.tuser.userid != mine }">
					<c:if test="${favoList.tuser.ffollowList[0].userid != mine}">
						<input id ="${favoList.tuser.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${favoList.tuser.userid})" value="フォロー" />
					</c:if>
					<c:if test="${favoList.tuser.ffollowList[0].userid == mine}">
						<input id="${favoList.tuser.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${favoList.tuser.userid})" value="フォロー解除" />
					</c:if>
				</c:if>
			</div>
		</div>
		<p class="timeLine_border"></p>

	</c:forEach>

	</div>
	</c:if>
	</tiles:put>
	</tiles:insert>
</body>
</html>
