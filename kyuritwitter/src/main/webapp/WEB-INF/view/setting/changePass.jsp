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

	<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/setting.css" />


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
							<li id="change3" class="active">
							<a href="changePassword" >パスワードを変える</a></li>
							<li id="change4" class="">
							<a href="makeList">リスト</a></li>
						</ul>
					</div>
				</div>

				<div class="span7 offset1">
				<div id="settingMain" class="setspan">
					<html:errors />
					<div>${successMsg}</div>

						<s:form styleClass="changePassForm">

							<h4>パスワードを変更する</h4>
							<label>パスワードを入力してください</label>
							<html:password property="oldpass" styleId="oldpass" />
							<label>新しいパスワードを入力してください</label>
							<html:password property="newpass" styleId="password" />

							<p>
								<input type="submit" id="changePass" name="changePasswordSubmit"
									class="btn" value="変更する" />
							</p>
						</s:form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

