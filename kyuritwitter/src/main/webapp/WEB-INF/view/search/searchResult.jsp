<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/search.css" />


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

		<c:if test="${empty tuserList }">
			<div>対象のユーザはみつかりません。</div>
		</c:if>

		<c:if test="${!empty tuserList}">
			<div class = "tuserMain">
			<div id="twitTitle"><h4>検索されたユーザ</h4></div>
			<p class="timeLine_border"></p>

				<c:forEach var="search" items="${tuserList}">
				<div id="${search.userid }" class="tuserListClass">
				<span class="pImg">
					<html:img src="${pageContext.request.contextPath}/main/showUserImg/${search.userid}"
							width="50" height="50" />
					</span>
					<s:link href="/userpage?userni=${search.userid}"> ${search.usernick} </s:link>

					<c:if test="${empty search.newMur || search.skey == 1}">
						<p class="noNewTwit">このユーザはつぶやいてないかもしくは非公開です。</p>
					</c:if>
					<c:if test="${!empty search.newMur }">
						<p class="newTwit">${search.newMur }</p>
						<p class="newTwitTime"><fmt:formatDate value="${search.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒"/></p>
					</c:if>
					<!-- ユーザがすでにフォローしているのかを検証する -->
					<div class="followButton">
					<c:if test="${search.userid != mine }">
						<c:if test="${search.ffollowList[0].userid != 1}">
							<input id ="${search.userid}followSub" type="button" class="btn btn-primary" onclick="follow(${search.userid})" value="フォロー" />
						</c:if>
						<c:if test="${search.ffollowList[0].userid == 1}">
							<input id="${search.userid}unfollowSub" type="button" class="btn btn-danger" onclick="unfollow(${search.userid})" value="フォロー解除" />
						</c:if>
					</c:if>
					</div>
				</div>
				<p class="timeLine_border"></p>
				</c:forEach>
				<div id="lastLine" class="0">　</div>
			</div>
		</c:if>
</tiles:put>
</tiles:insert>
</body>
</html>

