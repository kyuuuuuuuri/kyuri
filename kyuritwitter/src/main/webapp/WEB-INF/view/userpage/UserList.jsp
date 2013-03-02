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
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setting.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setList.css" />


	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" ></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/setting.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>


	<title>userList page</title>

</head>
<body>
	<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="followedpage" />
	<tiles:put name="content" type="string">


			<div id="settingMain" class="setspan">
				<span>リスト一覧</span>
				<p class="timeLine_border"></p>

				<div id = "ListLine">
				<html:errors />
				<c:if test="${empty listFollow}">
					<div id="noList">このユーザはリストを作っていません</div>
				</c:if>

				<c:if test="${not empty listFollow}">
					<c:forEach var="followList" items="${listFollow}">
					<div id="${followList.tlist.listid}" class="listLine">
						<s:link href="showListUser/${followList.tlist.listid}" styleClass="list">${followList.tlist.listname}</s:link>
						<span class="listdesc">${followList.tlist.listdesc}</span>
						<p class="makeUser">${followList.tlist.tuser.usernick} が作成しました</p>
					</div>
					</c:forEach>
				</c:if>

			</div>
		</div>

	</tiles:put>
	</tiles:insert>
</body>
</html>