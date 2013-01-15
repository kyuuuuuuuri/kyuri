<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Style-Type" content="text/css">
		<meta http-equiv="Content-Script-Type" content="text/javascript">
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginboot.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>


		<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

		<script type="text/javascript" charset="UTF-8">
		$(function(){
			$('#user_register').load('${f:url('login_register')}');
			});

		</script>

		<title>login</title>
	</head>

	<body>
		<div class="login_form container-fluid">

			<div class="row">
				<div class="top_comment span7">
					<h4>Twitterへようこそ<br>家族や友達、身近な生活の中や気になる会社やブランドで、今、何が起きているのかを見つけよう。</h4>
				</div>


				<div class="offset1 span4">
				<!-- <div class="loginformdiv">  -->

					<pre id="login">
					<s:form>
						<p><input type="text" name="UserName" class="form_style" id="userid" placeholder="ユーザ名、またはメールアドレス"/></p>
						<p><input type="password" name="Pass" autocomplete="off" class="form_style" id="pass" placeholder="パスワード"/></p>
					<p class="login_buton"><s:submit property = "loginSubmit">ログイン</s:submit></p>

					</s:form>
					</pre>

					<pre id="user_register">
					<s:form>
						<b>ユーザ登録(無料)</b>
						<s:submit property="userentry">ユーザ登録</s:submit>
					</s:form>
					</pre>

				</div>
			</div>
		</div>

	</body>
</html>