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
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/entrypage.css" />
		<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

		<title>You forget pass word ?</title>
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

	<pre>

		<h4>パスワードをお忘れですか？</h4>
		<s:form>
			<html:errors />
					<div class="control-group">
					<label for="usernick">ニックネームを入力してください</label>
					<html:text property="UserName" styleId="usernick"></html:text>

					<label class="control-label" for="question">ユーザ登録の時に設定した秘密の質問を設定してください</label>
					<div class="controls">

						<html:select property="question" styleId="question">
							<html:option value="1">出身はどこですか？</html:option>
							<html:option value="2">好きな映画はなんですか？</html:option>
							<html:option value="3">飼っている犬の名前はなんですか？</html:option>
						</html:select>
					</div>

					<label>秘密の答えを入力してください</label>
						<input type="text" id="answer" name="answer" class="form_style" value="" />
					</div>
					<s:submit property = "toMakeNewpass" styleClass="btn btn-primary">入力する</s:submit>


		</s:form>

	</pre>


	</body>
</html>