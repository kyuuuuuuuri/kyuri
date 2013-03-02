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
							<li id="change1" class=""><s:link href="setting">プロフィールを変える</s:link></li>
							<li id="change2" class="active"><s:link href="changeUserImg">画像を変える</s:link></li>
							<li id="change3" class=""><s:link href="changePassword">パスワードを変える</s:link></li>
							<li id="change4" class="">
							<s:link href="makeList">リスト</s:link></li>
						</ul>
					</div>
				</div>

				<div class="span7 offset1">
				<div id="settingMain" class="setspan">
						<s:form>

							<html:errors />

							<label>名前を変更する</label>
							<html:text property="username" styleId="username"value = "${mydata.username}" />

							<label>公開レベルを設定する</label>
							<html:checkbox styleId="keycheck" property="keycheck" value="1" />
							<span>非公開にする</span>

							<p>
								<s:submit styleId="changeUsernameSubmit"
									styleClass="btn btn-info" property="changeUsernameSubmit"
									value="変更する" />
							</p>
						</s:form>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>