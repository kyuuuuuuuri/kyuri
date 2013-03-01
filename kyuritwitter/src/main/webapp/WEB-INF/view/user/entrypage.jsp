<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" />

		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/entrypage.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/entrypage.js"></script>

		<title>entry</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav">
							<li><s:link href="/login/"><span class="header_font">アカウントをお持ちの場合はログイン</span></s:link></li>
						</ul>
					</div>
				</div>
			</div>

		<div>
		<pre>

			<h4>Twitterをはじめましょう</h4>

			<s:form styleClass="user_reg">
				<html:errors />
					<label>名前</label>
					<p><input type="text" name="userName" id="username" class="form_style" value="${tuser.userName}" /><span id="username_span" class="username_alert">名前入力してください</span></p>
					<label>ユーザネーム</label>
					<p><input type="text" name="userNick" class="form_style" id="usernick" value="${tuser.userNick}" /><span id="usernick_span" class="username_alert">ユーザネーム入力してください</span></p>
					<label>パスワード</label>
					<p><input type="text" name="pass" class="form_style" id="password" value="${tuser.password}" /><span id="password_span" class="username_alert">パスワード入力してください</span></p>
					<label>メールアドレス</label>
					<p><input type="text" name="mailAd" class="form_style" id="mailad" value="${tuser.mailAd}" /><span id="mailad_span" class="username_alert">メールアドレス入力してください</span></p>

					<div class="control-group">
					<label>パスワードを忘れたときのために、秘密の質問に答えてください</label>
					<div class="controls">
						<html:select property="question" styleId="select">
							<html:option value="1">出身はどこですか？</html:option>
							<html:option value="2">好きな映画はなんですか？</html:option>
							<html:option value="3">飼っている犬の名前はなんですか？</html:option>
						</html:select>
					</div>
					</div>
					<p><input type="text" id="secret_answer" name="answer" class="form_style" value=""/>
					<span id="answer_span" class="username_alert"></span></p>

				<input id="user_register" type="submit" class="btn btn-primary" name = "registerNewUser" value="アカウントを作成する"/>
			</s:form>
		</pre>
		</div>
	</body>
</html>
