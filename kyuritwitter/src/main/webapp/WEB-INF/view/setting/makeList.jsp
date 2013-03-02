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

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/setting.js"></script>

	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setting.css" />
	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setList.css" />

	<title>setting page</title>
	<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>
<body>

	<div id="listModal" class="modal hide fade">
		<div class="modal-header">
			<a href="#" class="close" data-dismiss="modal">&times;</a>
			<h4>新しいリストを作成する</h4>
		</div>
		<div id="modalBody" class="modal-body">
			<s:form>
				<label for="listName">リスト名</label>
				<html:text styleId="listName" property="listName"></html:text>
				<label for="listDesc">リストの説明</label>
				<html:textarea styleId="listDesc" property="listDesc"></html:textarea>
				<label>公開レベル</label>
					<html:checkbox styleId="keycheck" property="keycheck" value="1" />
					<span id="openLevel">非公開にする</span><br />
				<html:submit styleId="makeListSub" property="makeListName" value="リストを作成する" styleClass="btn btn-primary" />
			</s:form>
		</div>
		<div class="modal-footer">
			<a href="#" id="updateCancel" class="btn" data-dismiss="modal">Cancel</a>
		</div>
	</div>

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
						<a href="#listModal" id="makeListLink" data-toggle="modal" class="btn btn-success">リストを作成する</a>
					<p class="timeLine_border"></p>

					<div id = "ListLine">

					<html:errors />
					<c:if test="${empty listFollow}">
						<div id="noList">まだリストを利用してません</div>
					</c:if>

					<c:if test="${not empty listFollow}">
						<c:forEach var="tlist" items="${listFollow}">
							<a href="showListUser/${tlist.tlist.listid}" class="list">${tlist.tlist.listname}</a>
							<span class="listdesc">${tlist.tlist.listname}</span>
							<p class="makeUser">${tlist.tlist.tuser.usernick} が作成しました</p>

							<!-- if this user make this list -->
							<c:if test="${tlist.tlist.userid == mine}">
								<s:form>
									<html:hidden property="listid" value="${tlist.listid}"></html:hidden>
									<s:submit property="setList" styleClass="btn btn-info edit">編集</s:submit>
								</s:form>
							</c:if>
						</c:forEach>
						</c:if>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>