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

	<c:if test="${empty retweetsList}">
		<div id="noFollow">
			だれもリツイートしていません
		</div>
	</c:if>

	<c:if test="${!empty retweetsList}">
	<div id ="tuserMain">
	<c:forEach var="retweetsList" items="${retweetsList}">
		<div class="twitmain">
		<span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${retweetsList.tuser.userid}"
							width="50" height="50" />
					</span>
			<s:link href="userpage/${retweetsList.tuser.usernick}"> ${retweetsList.tuser.usernick} </s:link>
			<p>${retweetsList.tuser.newMur}</p>
			<p class="newTwitTime"><fmt:formatDate value="${retweetsList.tuser.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></p>

			<div class="followButton">
				<c:if test="${retweetsList.tuser.userid != mine }">
					<c:if test="${retweetsList.tuser.ffollowList[0].userid != mine}">
						<input id ="${retweetsList.tuser.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${retweetsList.tuser.userid})" value="フォロー" />
					</c:if>
					<c:if test="${retweetsList.tuser.ffollowList[0].userid == mine}">
						<input id="${retweetsList.tuser.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${retweetsList.tuser.userid})" value="フォロー解除" />
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
