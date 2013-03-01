<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="Content-Script-Type" content="text/javascript">
	<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>

	<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/setting.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/settingList.js"></script>


	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setting.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setList.css" />

	<title>setting page</title>
	<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>
<body>

	<div class="main_setting">
		<div class="container">
			<div class="row">
				<div class="span4">
					<div class="setspanMenu">
						<ul class="nav nav-list">
							<li class="nav-header">Menu</li>
							<li id="change1" class="">
							<a href="setting" >プロフィールを変える</a></li>
							<li id="change2" class="">
							<a href="changeUserImg" >画像を変える</a></li>
							<li id="change3" class="">
							<a href="changePassword">パスワードを変える</a></li>
							<li id="change4" class="active">
							<a href="makeList">リスト</a></li>
						</ul>
					</div>
				</div>

					<div class="span7 offset1">

					<div id="settingMain" class="setspan">
						<h4>${tlist.listname}</h4>
					<p class="timeLine_border"></p>

					<c:if test="${empty followList}">
						<div id="noList">だれかをフォローしましょう！</div>
					</c:if>

					<c:if test="${not empty followList}">

						<c:forEach var="followList" items="${followList}">
						<div id="${followList.ftuser.userid}" class = "ListUser">
							<a href="userpage/${followList.ftuser.usernick}" class="list">${followList.ftuser.usernick}</a>
							<span class="listUsername">${followList.ftuser.username}</span>

							<div class="changeListButton">
							<c:if test="${empty followList.ftuser.inListUserList[0].listid}">
								<input type="button" class="changeUserToListButton btn btn-info" onclick="insertToList(${followList.ftuser.userid},${tlist.listid})" value="リストに追加" />
							</c:if>

							<c:if test="${not empty followList.ftuser.inListUserList[0].listid}">
								<input type="button" class="changeUserToListButton btn btn-danger" onclick="deleteFromList(${followList.ftuser.userid},${tlist.listid})" value="リストから削除" />
							</c:if>
							</div>

						</div>
							<p class="timeLine_border"></p>
						</c:forEach>
					</c:if>
					<div id="lastLine" class="0">　</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>