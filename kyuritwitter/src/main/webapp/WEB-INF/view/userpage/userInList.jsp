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
	<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js" ></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/userList.js"></script>


	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setList.css" />


	<title>userList page</title>
	<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>
<body>
	<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="mainListpage" />
	<tiles:put name="content" type="string">

		<div id="timeLine">
				<h4>リスト名：${showTlist.listname}</h4>
				<p class="timeLine_border"></p>

				<c:if test="${empty tuserList}">
					<div id="noList">このリストの中にはユーザは登録されていません</div>
				</c:if>

				<c:if test="${not empty tuserList}">

					<c:forEach var="tuserList" items="${tuserList}">
						<div id="${tuserList.userid}" class="UserInList">
							<span> <html:img
									src="${pageContext.request.contextPath}/main/showUserImg/${tuserList.userid}"
									width="50" height="50" />
							</span>
							<s:link href="userpage/${tuserList.usernick}" styleClass="list">${tuserList.usernick}</s:link>

							<span class="listUsername">${tuserList.username}</span>
							<p class="userNewTwit"><span class="userNewTwitDesc">新しいつぶやき：</span>${tuserList.newMur}</p>

							<c:if test="${empty tuserList.ffollowList[0].userid}">
								<c:if test="${tuserList.userid != mine }">
									<input type="button" class="btn btn-primary" value="フォローする" />
								</c:if>
							</c:if>

							<c:if test="${not empty tuserList.ffollowList[0].userid}">
								<input type="button" class="btn btn-danger" value="フォローを解除する" />
							</c:if>

						</div>
						<p class="timeLine_border"></p>
					</c:forEach>
				</c:if>
				<div id="lastLine" class="0"></div>
			</div>
	</tiles:put>
	</tiles:insert>
</body>
</html>