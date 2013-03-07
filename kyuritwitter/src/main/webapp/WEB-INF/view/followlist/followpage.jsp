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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/followAjax.js"></script>

		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ja"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />

	<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
		<title>メインページ</title>
	</head>
<body>
	<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="followpage" />
	<tiles:put name="content" type="string">

		<div id="userdata">
		<span id = "userImg"><html:img src="${pageContext.request.contextPath}/main/showUserImg/${mydata.userid}" width="100" height="100" /></span>
		<p id = "usernickSuper">${mydata.usernick }</p>
		<div id="followButton">
		<c:if test = "${mydata.userid != mine}">
			<c:if test = "${mydata.ffollowList[0].userid != mine}">
				<input id ="followSub" type="button" class="btn btn-primary" onclick="follow(${mydata.userid})" value="フォロー" />
			</c:if>
			<c:if test = "${mydata.ffollowList[0].userid == mine}">
				<input id="unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${mydata.userid})" value="フォロー解除" />
			</c:if>
			<c:if test="${empty mydata.blockedUserList[0].userid}">
				<input id ="blockButton" type="button" class="btn btn-danger" onclick="block(${mydata.userid})" value="ブロックする" />
			</c:if>
			<c:if test="${not empty mydata.blockedUserList[0].userid}">
				<input id ="unBlockButton" type="button" class="btn btn-success" onclick="unBlock(${mydata.userid})" value="ブロックを解除する" />
			</c:if>

		</c:if>
		</div>

		</div>


	<c:if test="${empty followList}">
		<div id="noFollow">
			まだだれもフォローしていません
		</div>
	</c:if>

	<c:if test="${!empty followList}">
	<div id ="tuserMain">
		<p>${mydata.username }は${mydata.follow }人をフォローしています</p>

	<c:forEach var="followList" items="${followList}">
		<div id="${ followList.followid }" class="twitmain">
		<span class="pImg">
			<html:img src="${pageContext.request.contextPath}/main/showUserImg/${followList.ftuser.userid}"
					width="50" height="50" />
		</span>
			<s:link href="userpage/${followList.ftuser.usernick}"> ${followList.ftuser.usernick} </s:link>

			<p>${followList.ftuser.newMur}</p>
			<p class="newTwitTime">
			<fmt:formatDate value="${followList.ftuser.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" />
			</p>

			<div class="followButton">
				<c:if test="${followList.ftuser.userid != mine }">
					<c:if test="${followList.ftuser.ffollowList[0].userid != mine}">
						<input id ="${followList.ftuser.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${followList.ftuser.userid})" value="フォロー" />
					</c:if>
					<c:if test="${followList.ftuser.ffollowList[0].userid == mine}">
						<input id="${followList.ftuser.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${followList.ftuser.userid})" value="フォロー解除" />
					</c:if>
				</c:if>
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
