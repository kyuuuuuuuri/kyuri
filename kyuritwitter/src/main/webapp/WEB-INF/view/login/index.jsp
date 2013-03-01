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

		<script type="text/javascript" charset="UTF-8">
		$(function(){
			$('#user_register').load('${f:url('login_register')}');
			});

		</script>

		<title>login</title>
	</head>

	<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li><s:link href="/main/">
							<span class="header_font">ホーム</span>
						</s:link></li>
					<li class="divider-vertical"></li>
					<li><s:link href="/user/">
							<span class="header_font">ユーザ登録</span>
						</s:link></li>
					<li class="divider-vertical"></li>
					<li><s:link href="/login/">
							<span class="header_font">ログイン</span>
						</s:link></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="login_form container">

			<div class="row">
				<div class="top_comment span7">
					<h4>Twitterへようこそ<br>家族や友達、身近な生活の中や気になる会社やブランドで、今、何が起きているのかを見つけよう。</h4>
				</div>


				<div class="span4 offset1">
				<!-- <div class="loginformdiv">  -->

					<pre id="login">
					<s:form>
					<html:errors />
						<p><input type="text" name="UserName" class="form_style" id="userid" placeholder="ユーザ名"/></p>
						<p><input type="password" name="Pass" autocomplete="off" class="form_style" id="pass" placeholder="パスワード"/></p>
					<p class="login_buton"><s:submit property = "loginSubmit">ログイン</s:submit></p>
					</s:form>

						<html:link href="toSetPass">パスワードをお忘れですか？</html:link>
					</pre>

					<pre id="user_register">

					</pre>

				</div>
			</div>
		</div>

	</body>
</html>